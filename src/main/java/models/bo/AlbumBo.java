package models.bo;

import models.bean.Album;
import models.bean.Image;
import models.dao.AlbumDao;
import models.dao.ImageDao;
import utils.ImageUtil;

import javax.swing.text.ParagraphView;
import java.util.List;

public class AlbumBo {
    private AlbumDao albumDao = AlbumDao.getInstance();
    private ImageDao imageDao = ImageDao.getInstance();

    private static AlbumBo albumBo;

    private AlbumBo() {
    }

    public static AlbumBo getInstance() {
        if (albumBo == null) {
            albumBo = new AlbumBo();
        }
        return albumBo;
    }

    public boolean deleteAlbumByAlbumIds(int[] albumIds, int userId) {
        try{
            List<Image> images = imageDao.getImagesByAlbumIdsAndUserId(albumIds, userId);
            albumDao.deleteAlbumsByAlbumIdsAndUserId(albumIds, userId);
            if (images != null && images.size() > 0) {
                ImageUtil.deleteImages(images);

                int[] imageIds = new int[images.size()];
                for (int i = 0; i < imageIds.length; i++) {
                    imageIds[i] = images.get(i).getId();
                }

                imageDao.deleteImagesByImageIdsAndUserId(imageIds, userId);
            }
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
