package br.com.VH.framework.helper;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.swing.JFrame;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.exec.ExecuteException;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.genium_framework.appium.support.server.AppiumServer;
import com.github.genium_framework.server.ServerArguments;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.VH.framework.config.Constants;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

/**
 * @author Gabriel Fraga
 * 
 * Essa classe tem como finalidade ser uma classe helper,
 * contendo varios metodos que podem ser usados por diversas classes de forma generica
 * 
 * 
 */
public class Utils {

	private static final String DOT = ".";
	private static final String DASH = "_";
	private static final String BLANK = " ";
	private static final String COMMA = ":";
	private static Logger LOGGER = LoggerFactory.getLogger(Utils.class.getSimpleName());


	public static boolean isNumeric(String value) {
		return value.matches("[-+]?\\d*\\.?\\d+");	
	}

	public static BufferedImage createImageFromText(String text, Font font, Color color){
		int offset = 25;
		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = img.createGraphics();		
		FontMetrics fm = g2d.getFontMetrics();
		int width = fm.stringWidth(text) + offset;
		int height = fm.getHeight();

		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		g2d = img.createGraphics();
		g2d.setColor(color);
		g2d.setFont(font);
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g2d.drawString(text, 0, fm.getAscent());
		g2d.dispose();
		return img;
	}
	
	public static BufferedImage joinVerticallyBufferedImages(List<BufferedImage> imgs) {
		int width = 0;
		int height = 0;

		for (BufferedImage bufferedImage : imgs) {
			if(bufferedImage != null){
				width = (bufferedImage.getWidth() > width) ? bufferedImage.getWidth() : width;
				height += bufferedImage.getHeight();
			}
		}

		BufferedImage joinedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = joinedImage.createGraphics();
		Color oldColor = g2.getColor();
		//fill background
		g2.setPaint(Color.WHITE);
		g2.fillRect(0, 0, width, height);
		//draw image
		g2.setColor(oldColor);
		
		int x = 0;
		int y = 0;
		for (BufferedImage bufferedImage : imgs) {
			if(bufferedImage != null){
				g2.drawImage(bufferedImage, null, x, y);
				y += bufferedImage.getHeight();
			}
		}		
		g2.dispose();
		return joinedImage;
	}
	
	public static BufferedImage joinHorizontallyBufferedImages(List<BufferedImage> imgs) {
		int offset = 20;
		int width = 0;
		int height = 0;

		for (BufferedImage bufferedImage : imgs) {
			width += bufferedImage.getWidth();
			height = (bufferedImage.getHeight() > height) ? bufferedImage.getHeight() : height;
		}

		BufferedImage joinedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = joinedImage.createGraphics();
		Color oldColor = g2.getColor();
		//fill background
		g2.setPaint(Color.WHITE);
		g2.fillRect(0, 0, width, height);
		//draw image
		g2.setColor(oldColor);
		
		int x = 0;
		int y = 0;
		for (BufferedImage bufferedImage : imgs) {
			g2.drawImage(bufferedImage, null, x, y);
			x += bufferedImage.getWidth() - offset;
		}		
		g2.dispose();
		return joinedImage;
	}
	
	private static String[] splitAtCharacterCountForImageTranslation(String content){
		content = content.replaceAll("(.{80})", "$1|");
		String[] split = content.split("\\|");
	    return split;
	}
	
	public static String getDataHora(String format){

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date hora = Calendar.getInstance().getTime();
		String dataFormatada = sdf.format(hora);

		return dataFormatada;
	}

	public static void trustAllHosts() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }

            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }
        }};

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection
                    .setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public static String formatAttributeValue(String name, String localName) {
		return new StringBuilder().append(name).append('_').append(localName).toString().toUpperCase().replace('-', '_');
	}

    final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

	public String transformMillisToTime(long millis){

		String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
				TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
				TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));

		return hms;
	}
	
	/**
	 * Fabrica<BR>
	 *
	 * AUT-126 - Relatrio de execuo em Excel<BR>
	 *
	 * @since 29 de jul de 2016 10:51:25
	 * @author Gabriel Aguido Fraga<BR>
	 */
	public static String calculaPercentual(double valor, double total) {
		
		DecimalFormat fmt = new DecimalFormat("0.00");
				
		try {

			return fmt.format((valor * 100) / total);
		} 
		catch (Exception e) {

			return "0,00";
		}
	}
	
	/**
	 * Fabrica<BR>
	 *
	 * AUT-126 - Relatrio de execuo em Excel<BR>
	 *
	 * @since 11 de jul de 2016 10:55:24
	 * @author Gabriel Aguido Fraga<BR>
	 */
	public static String removeQuebraLinha (String s) {
		
		if (StringUtils.isNotBlank(s)) {
			
			s = s.replaceAll("\r", ""); 
			s = s.replaceAll("\t", "");
			s = s.replaceAll("\n", "");
		}
		
		return s;
	}
	
	/**
	 * 
	 * @author Gabriel Aguido Fraga
	 * @Fabrica
	 * 
	 * Mtodo responsvel por mover evidencias para a pasta 
	 * 
	 * @param sourcePath
	 * @param destPath
	 * @return
	 */
	public static void moveArquivo(final String pastaDestino, final File nomeArquivoOrigem) {
		File diretorioDestino = new File(pastaDestino);

		if (!diretorioDestino.exists()) {
			diretorioDestino.mkdirs();
		}
		boolean ok = nomeArquivoOrigem.renameTo(new File(diretorioDestino, nomeArquivoOrigem.getName()));
		if (ok) {
			System.out.println("Arquivo foi movido com sucesso");
		} else {
			System.out.println("Nao foi possivel mover o arquivo");
		}
	}

	public static WebDriver initializeChromeDriver() throws Exception {
//		WebDriver driver = new ChromeDriver(new DesiredCapabilities().chrome());
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		return driver;
		
		File file = new File(Constants.CHROMEDRIVER_EXE);

		if (!file.exists()) {

			throw new Exception("Erro ao localizar o driver");
		}
		
		ChromeDriverService chromeService = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File(Constants.CHROMEDRIVER_EXE))
				.usingAnyFreePort().build();

		chromeService.start();
		
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "about:blank");

		RemoteWebDriver driver = new RemoteWebDriver(chromeService.getUrl(), capabilities);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		return driver;
	}
		
	public static AppiumServer initializeServer() throws ExecuteException, IOException, InterruptedException, URISyntaxException{

		//Utils.uninstallAppiumSettingsApp();
		stopServer();
		ServerArguments serverArguments = new ServerArguments();
		serverArguments.setArgument("--port", 4723);
		serverArguments.setArgument("--local-timezone", true);
		serverArguments.setArgument("--address", "127.0.0.1");
		AppiumServer appiumServer = new AppiumServer(new File("C:\\Program Files (x86)\\Appium"), serverArguments);
		appiumServer.startServer(60000);
		return appiumServer;
	}
	
	public static void stopServer() throws IOException{

		StringBuilder command = new StringBuilder();
		command.append("cmd /c echo off")
		.append(" & ")
		.append("FOR /F \"usebackq tokens=5\" %p in (`netstat -nao ^| findstr /R /C:\"4723\"`) ")
		.append("do (FOR /F \"usebackq\" %t in (`TASKLIST /FI \"PID eq %p\" ^| findstr /I node.exe`) ")
		.append("do taskkill /F /PID %p)");

		Runtime.getRuntime().exec(command.toString());

	}
	
//	public static void closeEmulator() throws FileNotFoundException, IOException, URISyntaxException{
//		Runtime.getRuntime().exec(carregarLinks().getProperty(ViewConstants.Commands.ADB_PATH)+"adb emu kill");
//	}
	
	public static WebDriver initializeiOSDriver(String deviceName, String appPath) throws InterruptedException, IOException {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.PLATFORM,"iOS");
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.1");
		if (deviceName == null)	caps.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6 Plus"); else caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);//nome do dispositivo
		caps.setCapability(MobileCapabilityType.APP, appPath);
		//caps.setCapability(MobileCapabilityType.UDID, "8ab872d5a64f6e7fdcc7b5dab6c4914cb4b05ccf");

		Runtime.getRuntime().exec("open -a Simulator --args");

		return new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
	}
	
	public static boolean verificarSeHaDispositivosAndroidAtivos() throws IOException, URISyntaxException{

		Process proc = Runtime.getRuntime().exec(Constants.Commands.ADB_PATH+"adb shell getprop init.svc.bootanim");
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

		try{
			String s = "";
			if ((s = stdInput.readLine()).contains("stopped")) {
				//Quando encontrar o resultado do comando 'stopped', é porque o
				//dispositivo ja esta aberto e tudo está ok;
				System.out.println("Rodando testes em dispositivo físico");
				return true;
			}
		} catch (NullPointerException npe){
			return false;
		}

		return false;
	}
	
	private static String aguardarDispositivoAndroidLigar() throws IOException, InterruptedException, URISyntaxException{

		Process proc = Runtime.getRuntime().exec(Constants.Commands.ADB_PATH+"adb shell getprop init.svc.bootanim");

		BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

		try{
			// read the output from the command
			String s = "";
			if (!(s = stdError.readLine()).contains("HUEHUE")) {
				//enquanto não encontrar (nunca irá rs) a palavra 'HUEHUE', 
				//re-executa o comando de verificar se esta ativo o dispositivo;
				aguardarDispositivoAndroidLigar();
			}

		} catch(NullPointerException npe){
			String s = "";
			if (!(s = stdInput.readLine()).contains("stopped")) {
				//Quando encontrar o resultado do comando 'stopped', é porque o
				//dispositivo ja esta aberto e tudo está ok;
				aguardarDispositivoAndroidLigar();
			}
		}

		return "Dispositivo Carregado!";

	}
	
	public static AndroidDriver<AndroidElement> initializeAndroidDriver(String deviceName, String appPath) throws InterruptedException, IOException, URISyntaxException {

		DesiredCapabilities caps = new DesiredCapabilities();
		//caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0");
		caps.setCapability(MobileCapabilityType.PLATFORM,"Android");
		caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
		
		if (appPath == null) {
			caps.setCapability(MobileCapabilityType.APP, "Browser");
			caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
			//caps.setCapability("package", "com.asus.calculator");
			//caps.setCapability("activity", ".Calculator");
		} else {
			caps.setCapability(MobileCapabilityType.APP, new File(appPath).getAbsolutePath());
			//caps.setCapability(MobileCapabilityType.APP_PACKAGE, Constants.pacote);
			//caps.setCapability(MobileCapabilityType.APP_ACTIVITY, Constants.classe);
		}

		if (deviceName == null){ 
			caps.setCapability(MobileCapabilityType.DEVICE_NAME,"Emulator"); 
		} else {
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName); 
		}

		if(verificarSeHaDispositivosAndroidAtivos()){
			AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.MINUTES);
			return driver;
		} else {
			Runtime.getRuntime().exec(Constants.Commands.EMULATOR_PATH+"emulator -netdelay none -netspeed full -no-boot-anim -avd Nexus_5X_API23");
			System.out.println(aguardarDispositivoAndroidLigar());
			AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.MINUTES);
			return driver;
		}

	}
	
	public static void uninstallAppiumSettingsApp() throws FileNotFoundException, IOException, URISyntaxException{
		
		//adb uninstall com.test.app
		Runtime.getRuntime().exec(Constants.Commands.ADB_PATH+"adb uninstall io.appium.unlock");
		Runtime.getRuntime().exec(Constants.Commands.ADB_PATH+"adb uninstall io.appium.settings");
		
	}
	
	
	
}