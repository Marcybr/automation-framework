package br.com.framework.helper;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.exec.ExecuteException;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.github.genium_framework.appium.support.server.AppiumServer;
import com.github.genium_framework.server.ServerArguments;

import br.com.framework.config.Constants;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.nio.file.*;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * @author marcia.cardoso
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

		File file = null;
		ChromeDriverService chromeService = null;
		String os = System.getProperty("os.name").toLowerCase();

		if (os.contains("win")){
			//Operating system is based on Windows
			file = new File(Constants.CHROMEDRIVER_EXE);

			if (!file.exists()) {
				throw new Exception("Erro ao localizar o driver");
			}
			chromeService = new ChromeDriverService.Builder()
					.usingDriverExecutable(new File(Constants.CHROMEDRIVER_EXE))
					.usingAnyFreePort().build();

		} else if (os.contains("x") || os.contains("mac") || os.contains("osx")){
			//Operating system is Apple OSX based
			file = new File(Constants.CHROMEDRIVER_MAC);

			if (!file.exists()) {
				throw new Exception("Erro ao localizar o driver");
			}
			chromeService = new ChromeDriverService.Builder()
					.usingDriverExecutable(new File(Constants.CHROMEDRIVER_MAC))
					.usingAnyFreePort().build();

		} else if (os.contains("nix") || os.contains("aix") || os.contains("nux")){
			//Operating system is based on Linux/Unix/*AIX
			file = new File(Constants.CHROMEDRIVER_LINUX);

			if (!file.exists()) {
				throw new Exception("Erro ao localizar o driver");
			}
			chromeService = new ChromeDriverService.Builder()
					.usingDriverExecutable(new File(Constants.CHROMEDRIVER_LINUX))
					.usingAnyFreePort().build();
		}

		chromeService.start();

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "about:blank");

		RemoteWebDriver driver = new RemoteWebDriver(chromeService.getUrl(), capabilities);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		return driver;
	}

	public static AppiumServer initializeServer() throws ExecuteException, IOException, InterruptedException, URISyntaxException{

		Utils.uninstallAppiumSettingsApp();
		stopServer();
		ServerArguments serverArguments = new ServerArguments();
		serverArguments.setArgument("--port", 4723);
		serverArguments.setArgument("--local-timezone", true);
		serverArguments.setArgument("--address", "127.0.0.1");
		AppiumServer appiumServer = null;
		
		String os = System.getProperty("os.name").toLowerCase();
		
		if(os.contains("win")){
			appiumServer = new AppiumServer(new File("C:\\Program Files (x86)\\Appium"), serverArguments);
		} else if(os.contains("x") || os.contains("mac")|| os.contains("osx")){
			appiumServer = new AppiumServer(new File("/usr/local/bin/node"), 
					new File("/Applications/Appium.app/Contents/Resources/app/node_modules/appium/build/lib/main.js"), serverArguments);
		} else if (os.contains("nix") || os.contains("aix") || os.contains("nux")){
			appiumServer = new AppiumServer(new File("~/Applications/Appium.app"), serverArguments);
		}
		
		appiumServer.startServer(20000);
		return appiumServer;
	}

	public static void stopServer() throws IOException{

		String os = System.getProperty("os.name").toLowerCase();
		Process proc = null;

		if (os.contains("win")){
			StringBuilder command = new StringBuilder();
			command.append("cmd /c echo off")
			.append(" & ")
			.append("FOR /F \"usebackq tokens=5\" %p in (`netstat -nao ^| findstr /R /C:\"4723\"`) ")
			.append("do (FOR /F \"usebackq\" %t in (`TASKLIST /FI \"PID eq %p\" ^| findstr /I node.exe`) ")
			.append("do taskkill /F /PID %p)");

			Runtime.getRuntime().exec(command.toString());

		} else if (os.contains("x") || os.contains("mac") || os.contains("osx")){
			Runtime.getRuntime().exec("killall node");

		} else if (os.contains("nix") || os.contains("aix") || os.contains("nux")){
			Runtime.getRuntime().exec("killall node");

		}

	}

	public static void closeEmulator() throws FileNotFoundException, IOException, URISyntaxException{

		String os = System.getProperty("os.name").toLowerCase();

		if (os.contains("win")){
			Runtime.getRuntime().exec(Constants.ADB_PATH_WIN+"adb emu kill");
			
		} else if (os.contains("x") || os.contains("mac") || os.contains("osx")){
			Runtime.getRuntime().exec(Constants.ADB_PATH_MAC+"adb emu kill");
			Runtime.getRuntime().exec("killall \"Simulator\"");

		} else if (os.contains("nix") || os.contains("aix") || os.contains("nux")){
			Runtime.getRuntime().exec(Constants.ADB_PATH_LINUX+"adb emu kill");
		}
	}

	public static IOSDriver<MobileElement> initializeiOSDriver(String deviceName, String appPath) throws InterruptedException, IOException {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.1");
		
		if (deviceName == null) {
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone XR"); 
		} else {
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		}
		
		if (appPath == null){
			caps.setCapability("bundleId", "com.apple.Maps");
		} else {
			caps.setCapability(MobileCapabilityType.APP, appPath);			
		}
	
		//caps.setCapability(MobileCapabilityType.UDID, "F60D6E1D-3C03-4D99-AEF6-90E79E5D3CE8");

		Runtime.getRuntime().exec("open -a Simulator --args");

		IOSDriver<MobileElement> driver = new IOSDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	public static boolean verificarSeHaDispositivosAndroidAtivos() throws IOException, URISyntaxException{

		String os = System.getProperty("os.name").toLowerCase();
		Process proc = null;

		if (os.contains("win")){
			proc = Runtime.getRuntime().exec(Constants.ADB_PATH_WIN+" adb shell getprop init.svc.bootanim");		    

		} else if (os.contains("x") || os.contains("mac") || os.contains("osx")){
			String[] args = new String[] {"/bin/bash", "-l", "-c", Constants.ADB_PATH_MAC + "adb shell getprop init.svc.bootanim"};
			proc = new ProcessBuilder(args).start();

		} else if (os.contains("nix") || os.contains("aix") || os.contains("nux")){
			proc = Runtime.getRuntime().exec(Constants.ADB_PATH_LINUX+" adb shell getprop init.svc.bootanim");

		}

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

		String os = System.getProperty("os.name").toLowerCase();
		Process proc = null;

		if (os.contains("win")){
			proc = Runtime.getRuntime().exec(Constants.ADB_PATH_WIN+" adb shell getprop init.svc.bootanim");		    

		} else if (os.contains("x") || os.contains("mac") || os.contains("osx")){
			String[] args = new String[] {"/bin/bash", "-l", "-c", Constants.ADB_PATH_MAC +"adb shell getprop init.svc.bootanim"};
			proc = new ProcessBuilder(args).start();

		} else if (os.contains("nix") || os.contains("aix") || os.contains("nux")){
			String[] args = new String[] {"/bin/bash", "-l", "-c", Constants.ADB_PATH_LINUX, " adb shell getprop init.svc.bootanim"};
			proc = new ProcessBuilder(args).start();

		}

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

	public static AndroidDriver<MobileElement> initializeAndroidDriver(String deviceName, String appPath) throws InterruptedException, IOException, URISyntaxException {

		DesiredCapabilities caps = new DesiredCapabilities();
		//caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0");
		caps.setCapability(MobileCapabilityType.PLATFORM,"Android");
		caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);

		if (appPath == null) {
			//caps.setCapability(MobileCapabilityType.APP, "com.google.android.apps.maps");
			//caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
			caps.setCapability("appPackage", "com.google.android.apps.maps");
			caps.setCapability("appActivity", "com.google.android.maps.MapsActivity");
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
			AndroidDriver<MobileElement> driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			return driver;
		} else {

			String os = System.getProperty("os.name").toLowerCase();

			if (os.contains("win")){
				Runtime.getRuntime().exec(Constants.EMULATOR_PATH_WIN+
						"emulator -netdelay none -netspeed full -no-boot-anim -avd Nexus_5X_API_25");		    

			} else if (os.contains("x") || os.contains("mac") || os.contains("osx")){
				String[] args = new String[] {"/bin/bash", "-l", "-c", 
						Constants.EMULATOR_PATH_MAC +
				"emulator -netdelay none -netspeed full -no-boot-anim -avd Nexus_5X_API_25"};
				new ProcessBuilder(args).start();

			} else if (os.contains("nix") || os.contains("aix") || os.contains("nux")){
				String[] args = new String[] {"/bin/bash", "-c", 
						Constants.EMULATOR_PATH_LINUX, 
				"emulator -netdelay none -netspeed full -no-boot-anim -avd Nexus_5X_API_25"};
				new ProcessBuilder(args).start();			
			}

			System.out.println(aguardarDispositivoAndroidLigar());
			AndroidDriver<MobileElement> driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.MINUTES);
			return driver;
		}

	}

	public static void uninstallAppiumSettingsApp() throws FileNotFoundException, IOException, URISyntaxException{

		String os = System.getProperty("os.name").toLowerCase();
		Process proc = null;

		if (os.contains("win")){
			//adb uninstall com.test.app
			Runtime.getRuntime().exec(Constants.ADB_PATH_WIN+" adb uninstall io.appium.unlock");
			Runtime.getRuntime().exec(Constants.ADB_PATH_WIN+" adb uninstall io.appium.settings");		    

		} else if (os.contains("x") || os.contains("mac") || os.contains("osx")){
			//adb uninstall com.test.app
			String[] args = new String[] {"/bin/bash", "-l", "-c", Constants.ADB_PATH_MAC + "adb uninstall io.appium.unlock"};
			new ProcessBuilder(args).start();

			args = new String[] {"/bin/bash", "-l", "-c", Constants.ADB_PATH_MAC + "adb uninstall io.appium.settings"};
			new ProcessBuilder(args).start();

		} else if (os.contains("nix") || os.contains("aix") || os.contains("nux")){
			//adb uninstall com.test.app
			String[] args = new String[] {"/bin/bash", "-c", Constants.ADB_PATH_LINUX, " adb uninstall io.appium.unlock"};
			new ProcessBuilder(args).start();

			args = new String[] {"/bin/bash", "-c", Constants.ADB_PATH_LINUX, " adb uninstall io.appium.settings"};
			new ProcessBuilder(args).start();

		}

	}



}