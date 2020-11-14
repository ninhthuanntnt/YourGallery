package tasks;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.File;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ThumbnailTask implements Runnable{
    private String filePath;
    private String outputFile;

    public ThumbnailTask(String filePath, String outputFile){
        this.filePath = filePath;
        this.outputFile = outputFile;
    }

    @Override
    public void run() {
        String format = filePath.substring(filePath.lastIndexOf(".") + 1);
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), 5000);
            BufferedImage bufferedImage = ImageIO.read(new File(filePath));
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            ImageIO.write(bufferedImage, format, dos);
            dos.flush();
            socket.shutdownOutput();

            BufferedImage thumbnail = ImageIO.read(socket.getInputStream());

            ImageIO.write(thumbnail, format, new File(outputFile));

            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}