package com.gmall.manager.utils;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.spel.CodeFlow;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URL;

/**
 * ClassName PmsUploadTest
 * PackageName com.gmall.manager.utils
 *
 * @Description
 * @auther wang
 * @create 2020-07-07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PmsUploadTest {


    @Test
    public void test() {
//        String tracker = PmsUploadTest.class.getResource("/tracker.conf").getPath();
        // 配置fdfs的全局链接地址
        String tracker = PmsUploadTest.class.getResource("/tracker.conf").getPath();// 获得配置文件的路径

        try {
            ClientGlobal.init(tracker);
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getTrackerServer();

            StorageClient storageClient = new StorageClient(trackerServer);


            String[] uploadInfos = storageClient.upload_file("C:\\Users\\Administrator\\Desktop\\图片\\00000.jpg", "jpg", null);

            String url = "http://192.168.0.133";

            for (String uploadInfo : uploadInfos) {
                url += "/"+uploadInfo;

                //url = url + uploadInfo;
            }

            System.out.println("====="+url);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
