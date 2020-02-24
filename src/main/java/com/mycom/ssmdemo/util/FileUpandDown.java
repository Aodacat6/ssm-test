package com.mycom.ssmdemo.util;

import com.mycom.ssmdemo.common.commexception.BizException;
import com.mycom.ssmdemo.common.commonutil.CommonUtils;
import com.mycom.ssmdemo.common.message.CommResult;
import com.mycom.ssmdemo.common.message.ResponseData;
import com.mycom.ssmdemo.constants.MyConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.Name;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-22 下午 07:21
 * @description： 单文件上传下载
 * @modified By：
 * @version: $
 */
@Component
public class FileUpandDown {
    /**
     * MultipartFile这个类可以用于接收上传的文件
     *
     * @param file
     * @return
     */
    public CommResult fileUpload(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new BizException("未选择要上传的文件，请重新选择！");
        }

        String filePath = new File("upload").getAbsolutePath();
        File fileUpload = new File(filePath);
        if (!fileUpload.exists()) {
            //mkdir()只能创建一层目录
            //mkdirs()可以创建所需要的全部目录，包括父类目录
            fileUpload.mkdirs();
        }

        fileUpload = new File(filePath, file.getOriginalFilename());
        if (fileUpload.exists()) {
            throw new BizException("文件已存在，不要重复上传");
        }
        //获取文件后缀名
        //只有指定格式的图片才能上传

        String suffixName = fileUpload.getAbsolutePath().substring(fileUpload.getAbsolutePath().indexOf(".") + 1);
        if (!(MyConstants.IMG_TYPE_BMP.equals(suffixName.toUpperCase())
                || MyConstants.IMG_TYPE_GIF.equals(suffixName.toUpperCase())
                || MyConstants.IMG_TYPE_JPEG.equals(suffixName.toUpperCase())
                || MyConstants.IMG_TYPE_JPG.equals(suffixName.toUpperCase())
                || MyConstants.IMG_TYPE_PNG.equals(suffixName.toUpperCase()))) {
            throw new BizException("上传的图片不是有效的格式，请选择正确的格式上传！");
        }

        //判断文件大小，不能超过20M
        if (!CommonUtils.checkFileSize(fileUpload.length(), 20, "M")) {
            throw new BizException("上传失败，文件不能超过20M");
        }

        try {
            //这句话才是最有用的
            file.transferTo(fileUpload);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("fileName", file.getOriginalFilename());
        map.put("fileDir", fileUpload.getAbsolutePath());
        return CommResult.ok(map);
    }

    /**
     * 文件下载
     */
    public void fileDown(String fileName, HttpServletResponse response) throws IOException {

        File file = new File("upload" + File.separator + fileName);
        if (!file.exists()) {
            throw new BizException("文件不存在，无法下载！");
        }

        //response.setCharacterEncoding("UTF-8");
        response.setContentType("application/force-download");
        //response.setContentType("image/jpeg");
        //URLEncoder.encode(fileName, "UTF-8")解决了文件名为中文无法下载的问题
        response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));

        byte[] buffer = new byte[1024];

        BufferedInputStream inputStream = new BufferedInputStream(
                new FileInputStream(file)
        );

        OutputStream outputStream = response.getOutputStream();

        int i;
        try {
            while ((i = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, i);
            }
        } finally {
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }


    }
}
