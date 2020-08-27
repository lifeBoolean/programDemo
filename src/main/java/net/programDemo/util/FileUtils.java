package net.programDemo.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.jpeg.JpegDirectory;

import net.programDemo.board.model.BoardVo;

@Component("fileUtils")
public class FileUtils {
	private static final String filePath = "D:\\uploads\\files\\";
	
	public List<Map<String, Object>> parseInsertFileInfo(BoardVo boardVo, MultipartHttpServletRequest mpRequest) throws Exception{
		
		// 서버의 context 경로 구하기
//	    String rootPath = mpRequest.getSession().getServletContext().getRealPath("/");
//	    System.out.println("rootPath: " + rootPath);
	    
//	    String filePath = rootPath + "upload\\"; // 파일이 저장될 위치
//		String filePath = "D:\\upload\\files\\"; // 파일이 저장될 위치
	    
		int idx = boardVo.getIdx();
		System.out.println("getIdx: " + idx);
		
		File folderPath = new File(filePath);
		// 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
		if (!folderPath.exists()) {
			try{
				folderPath.mkdir(); //폴더 생성합니다.
			    System.out.println("폴더가 생성되었습니다.");
		        } 
		        catch(Exception e){
			    e.getStackTrace();
			}        
	         }else {
			System.out.println("이미 폴더가 생성되어 있습니다.");
		}
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		List<MultipartFile> fileList = mpRequest.getFiles("files");
		
		for (MultipartFile multipartFile : fileList) {
			if(!multipartFile.isEmpty()) {
				String originalFileName = multipartFile.getOriginalFilename(); // 원본 파일 명
				String originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				String storedFileName = UUID.randomUUID().toString().replaceAll("-", "") + originalFileExtension;
				long fileSize = multipartFile.getSize(); // 파일 사이즈
				String newFile = filePath + storedFileName;
	
	            System.out.println("originalFileName : " + originalFileName);
	            System.out.println("originalFileExtension : " + originalFileExtension);
	            System.out.println("storedFileName : " + storedFileName);
	            System.out.println("fileSize : " + fileSize);
	            System.out.println("newFile : " + newFile);
	            
	            multipartFile.transferTo(new File(newFile));
	            
	            // DB에 입력
	            Map<String, Object> listMap = new HashMap<String, Object>();
	            listMap.put("IDX", idx);
	            listMap.put("ORG_FILE_NAME", originalFileName);
	            listMap.put("STORED_FILE_NAME", storedFileName);
	            listMap.put("FILE_SIZE", fileSize);
	            list.add(listMap);
	            
//				썸네일
	            String suffix = originalFileExtension.substring((originalFileExtension.lastIndexOf(".")+1)).toLowerCase();
	            String thumbPath= filePath + "s_" +storedFileName;
	            int newWidth = 1920;
	            int newHeight = 1080;
	            double ratio;
	            int w;
	            int h;
	            
	            File orgFile = new File(newFile);
	            BufferedImage orgImg = ImageIO.read(orgFile);
	            
	         // 원본 파일의 Orientation 정보를 읽는다.
	        	int orientation = 1; // 회전정보, 1. 0도, 3. 180도, 6. 270도, 8. 90도 회전한 정보
	        	int width = 0; // 이미지의 가로폭
	        	int height = 0; // 이미지의 세로높이
	         
	        	Metadata metadata; // 이미지 메타 데이터 객체
	        	Directory directory; // 이미지의 Exif 데이터를 읽기 위한 객체
	        	JpegDirectory jpegDirectory; // JPG 이미지 정보를 읽기 위한 객체
	         
	        	try {
	        		metadata = ImageMetadataReader.readMetadata(orgFile);
	        		directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
	        		jpegDirectory = metadata.getFirstDirectoryOfType(JpegDirectory.class);
	        		if(directory != null){
	        			orientation = directory.getInt(ExifIFD0Directory.TAG_ORIENTATION); // 회전정보
	        			System.out.println("orientation===============" + orientation);
	        		}
	         
	        	}catch (Exception e) {
	        		orientation=1;
	        	}	            
	            
	            
	            // 회전 시킨다.
	        	switch (orientation) {
	        	case 6:
	        		orgImg = Scalr.rotate(orgImg, Scalr.Rotation.CW_90, null); 
	        		break;
	        	case 1:	         
	        		break;
	        	case 3:
	        		orgImg = Scalr.rotate(orgImg, Scalr.Rotation.CW_180, null);
	        		break;
	        	case 8:
	        		orgImg = Scalr.rotate(orgImg, Scalr.Rotation.CW_270, null);
	        		break;	         
	        	default:
	        		orientation=1;
	        		break;
	        	}
	            
	            int orgImgWidth = orgImg.getWidth();
	            int orgImgHeight = orgImg.getHeight();
	            
	            System.out.println("imgFormat: " + suffix);
	            System.out.println("imageWidth: " + orgImgWidth);
	            System.out.println("imageHeight: " + orgImgHeight);
	            
	            if(orgImgWidth > orgImgHeight) {
	            	System.out.println("width 가 커요");
	            	ratio = (double) newWidth / orgImgWidth;
	                w = (int)(orgImgWidth * ratio);
	                h = (int)(orgImgHeight * ratio);
	            } else if(orgImgWidth < orgImgHeight) {
	            	System.out.println("height 가 커요");
	            	ratio = (double) newHeight / orgImgHeight;
	                w = (int)(orgImgWidth * ratio);
	                h = (int)(orgImgHeight * ratio);
	            } else {
	            	System.out.println("넓이 오류");
	            	w = newWidth;
	                h = newHeight;
	            }
	            
	            System.out.println("w: " + w);
	            System.out.println("h: " + h);
	            
	            Image resizeImage = orgImg.getScaledInstance(w, h,  Image.SCALE_SMOOTH);	            
	            BufferedImage resized = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	            Graphics2D g2d  = resized.createGraphics();
	            g2d.drawImage(resizeImage, 0, 0, null);
	            g2d.dispose();
	            ImageIO.write(resized, suffix, new File(thumbPath));
	            
			}            
		}
		
		return list;
		
	}
		

}
