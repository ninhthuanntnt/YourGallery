package models.bo;

import models.bean.Image;
import models.dao.ImageDao;
import utils.ImageUtil;

import java.io.File;
import java.util.List;

public class ImageBo {
    private static ImageBo imageBo;
    private ImageDao imageDao = ImageDao.getInstance();

    private ImageBo(){ }

    public static ImageBo getInstance() {
        if(imageBo == null){
            imageBo = new ImageBo();
        }
        return imageBo;
    }

    public boolean deleteImagesByImageIdsAndUserId(int[] imgIds, int userId){
        try {
            List<Image> images = imageDao.getImagesByImageIdsAndUserId(imgIds, userId);

            imageDao.deleteImagesByImageIdsAndUserId(imgIds, userId);
            ImageUtil.deleteImages(images);

        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
}
