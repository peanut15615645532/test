import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class showLove {
    public static void main(String[] args) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("notepad");
        //开启线程播放音乐
        Thread thread = new Thread(()->{
            //播放音乐
            MusicUtil.play("love.mp3");
        });
        thread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //读取文件
        readFile("mylove.txt");
    }

    /**
     * 读取文件
     * @param path 文件的路径
     */
    public static void readFile(String path){
        //使用字符流读取文件中的内容
        try (FileReader fileReader = new FileReader(path)) {
            int ch;
            while((ch=fileReader.read()) != -1){
                copy(String.valueOf((char)ch));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 将数据粘贴到文件中
     * @param ch
     */
    public static void copy(String ch){
        //ctrl + c
        //创建StringSelection对象，将ch放入
        StringSelection stringSelection = new StringSelection(ch);
        //ToolKit可以将数据放到剪切板中
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection,stringSelection);

        //ctrl + v
        //通过Robot可以模拟键盘的输入
        Robot robot = null;
        try {
            robot= new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        if (robot != null) {
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Random random = new Random();
            int number = random.nextInt(300);
            robot.delay(number>200?number:number+200);
        }

    }
}
