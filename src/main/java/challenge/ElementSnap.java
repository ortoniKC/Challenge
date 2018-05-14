package challenge;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ElementSnap {
ChromeDriver driver;
@Test
	public void main() throws IOException {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.irctc.co.in/eticketing/loginHome.jsf");
		WebElement button = driver.findElementById("loginbutton");
		elementScreenshot(button);
		driver.quit();
	}

	public void elementScreenshot(WebElement ele) throws IOException {
		File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotAs, new File("./Snap/snap.jpg"));
		int x = ele.getLocation().getX();
		int y = ele.getLocation().getY();
		int width = ele.getSize().getWidth();
		int height = ele.getSize().getHeight();
		BufferedImage img = ImageIO.read(screenshotAs);
		BufferedImage subimage = img.getSubimage(x, y, width, height);
		ImageIO.write(subimage, "png", new File("./Snap/cropped.png"));
	}

}
