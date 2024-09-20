package ims.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;


/**
 * 画像ファイルを AWS S3 bucketにアップロードする.
 *
 * @author R.Yazaki
 * @version 1.0.0
 */
@Service
public class ImageUploadService {
	@Autowired
	private AmazonS3 amazonS3;
	
	@Value("${aws.s3.bucket.name}")
    private String bucketName;
	
	/*
	 * 画像ファイルを AWS S3 bucketにアップロードする.
	 * 
	 * @params multipart file, folder name, file name
	 * @return 画像url
	 */
	public String uploadImg(MultipartFile multipartFile,
			String folder, String origFileName) {
		LocalDateTime currTime = LocalDateTime.now();
		String fileName = origFileName +
				currTime.toString()
		            .replace(" ", "-").replace(":", "");
		String filePath = folder + "/" + fileName;
		try {
			// multipart file を fileに変換.
			File file = convertMultipartFileToFile(multipartFile);
			// 指定のパスにファイルをアップロード
			amazonS3.putObject(bucketName, filePath, file);
			file.delete();
		} catch (Exception e){
			return null;
		}
		return filePath;
	}
	
	/*
	 * AWS S3 bucketから画像ファイルを削除.
	 * 
	 * @params file name
	 * @return リターンコード
	 */
	public boolean deleteImg(final String fileName) {
		final DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucketName, fileName);
	    try {
	    	amazonS3.deleteObject(deleteObjectRequest);
	    } catch (AmazonServiceException e) {
            // Amazon S3 exception
            e.printStackTrace();
            return false;
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            e.printStackTrace();
            return false;
        }
	    return true;
	}
	
	/*
	 * Multipart file を fileに変換
	 * 
	 * @params multipart file
	 * @return ファイル
	 */
	public File convertMultipartFileToFile(MultipartFile file) throws IOException {
		File convertedFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convertedFile);
		fos.write(file.getBytes());
		fos.close();		
		return convertedFile;
	}
}
