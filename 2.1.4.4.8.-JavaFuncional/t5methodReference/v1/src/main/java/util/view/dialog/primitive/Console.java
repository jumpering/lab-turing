package util.view.dialog.primitive;

import util.values.Pair;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.regex.Pattern;

public class Console {

	private static Console console;

	public static Console instance() {
		if (Console.console == null){
			Console.console = new Console();
		}
		return Console.console;
 }

 private static String getMainClassName() {
	 for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
		 if ("main".equals(element.getMethodName())) {
			 return element.getClassName();
		 }
	 }
	 return "unknown";
 }

	private static final String mainClassName = getMainClassName();
	private static final String mainClassSimpleName = mainClassName.substring(mainClassName.lastIndexOf('.') + 1);
	private static final String logFolderName = mainClassSimpleName;

	static final Pair<String,String> CHAR_regExpPair = new Pair<String,String> ("CHAR" ,"c");
	public static final Pair<String,String> INTEGER_regExpPair = new Pair<String,String> ("INTEGER" ,"-?\\d+");
	public static final Pair<String,String> DOUBLE_regExpPair = new Pair<String,String> ( "DOUBLE" ,"-?(\\d+(\\.\\d+)?([eE][+-]?\\d+)?|\\.\\d+([eE][+-]?\\d+)?)");
	
	private static final String EXTENSION = ".log";
	private static final String BASE_PATH = System.getProperty("user.dir");
 	private static final String HEAD_PATH = BASE_PATH + "/resources/logs/" + logFolderName + "/" ;

	private static String TAIL_PATH = "-"
			+ LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "-"
			+ LocalTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss")) + EXTENSION;
	private static String INPUT_PATH = HEAD_PATH + "input" + TAIL_PATH;
	private static String INPUT_OUTPUT_PATH = HEAD_PATH + "inputOutput" + TAIL_PATH;

	private static BufferedReader input;
	private static PrintStream output;
	private static PrintWriter inputLog;
	private static PrintWriter inputOutputLog;
	static {
		Console.input = new BufferedReader(new InputStreamReader(System.in));
		Console.output = System.out;
		try {
   File logDir = new File(HEAD_PATH);
   if (!logDir.exists()) {
	    logDir.mkdirs();
   } 
			Console.inputLog = new PrintWriter(INPUT_PATH);
			Console.inputOutputLog = new PrintWriter(INPUT_OUTPUT_PATH);
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}

	public static void close(){
		Console.close("0");
	}

	public static void close(String suffix) {
		try {
			if (Console.inputLog != null) {
				Console.inputLog.close();
			}
			if (Console.inputOutputLog != null) {
				Console.inputOutputLog.close();
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		String inputLogPath = HEAD_PATH + "input-scenario-" + suffix + EXTENSION;
		String inputOutputLogPath = HEAD_PATH + "inputOutput-scenario-" + suffix + EXTENSION;
		File oldInputLog = new File(inputLogPath);
		File oldInputOutputLog = new File(inputOutputLogPath);
		if (oldInputLog.exists()) {
			oldInputLog.delete();
		}
		if (oldInputOutputLog.exists()){
			oldInputOutputLog.delete();
		}
		new File(INPUT_PATH).renameTo(oldInputLog);
		new File(INPUT_OUTPUT_PATH).renameTo(oldInputOutputLog);
	}

	private Console(){
	}

	public String readString() {
		return this.readString("");
	}

	public String readString(String title) {
		assert title != null;

		String string = "";
		this.write(title);
		try {
			string = Console.input.readLine();
			Console.inputLog.println(string);
			Console.inputOutputLog.println(string);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return string;
	}

	public void write(String string) {
		assert string != null;

		Console.output.print(string);
		Console.inputOutputLog.print(string);
	}

	public void writeln(String string) {
		this.write(string + "\n");
	}

	public void writeln() {
		this.writeln("");
	}

	public char readChar() {
		return this.readChar("");
	}

	public char readChar(String title) {
		assert title != null;

		String string = "";
		final Pattern charPattern = Pattern.compile(CHAR_regExpPair.getValue());
		char characterInput = ' ';
		boolean ok;
		do {
			string = this.readString(title);
			ok = charPattern.matcher(string).find();
			if (ok) {
				characterInput = string.charAt(0);
			} else {
				this.writeError(charPattern.toString());
			}
		} while (!ok);
		return characterInput;
	}

	public void write(char character) {
		Console.output.print(character);
		Console.inputOutputLog.print(character);
	}

	public void writeln(char character) {
		this.write(character + "\n");
	}

	public int readInt() {
		return this.readInt("");
	}

	public int readInt(String title) {
		assert title != null;

		String string = "";
		final Pattern intPattern = Pattern.compile(INTEGER_regExpPair.getValue());
		int intInput = ' ';
		boolean ok;
		do {
			string = this.readString(title);
			ok = intPattern.matcher(string.trim()).find();
			if (ok) {
				intInput = Integer.parseInt(string);
			} else {
				this.writeError(intPattern.toString());
			}
		} while (!ok);
		return intInput;
	}

	public void write(int value) {
		Console.output.print(value);
		Console.inputOutputLog.print(value);
	}

	public void writeln(int value) {
		this.write(value + "\n");
	}

	public double readDouble() {
		return this.readInt("");
	}

	public double readDouble(String title) {
		assert title != null;

		String string = "";
		final Pattern doublePattern = Pattern
				.compile(DOUBLE_regExpPair.getValue());
		double doubleInput = ' ';
		boolean ok;
		do {
			string = this.readString(title);
			ok = doublePattern.matcher(string.trim()).find();
			if (ok) {
				doubleInput = Integer.parseInt(string);
			} else {
				this.writeError(doublePattern.toString());
			}
		} while (!ok);
		return doubleInput;
	}

	public void write(double value) {
		Console.output.print(value);
		Console.inputOutputLog.print(value);
	}

	public void writeln(double value) {
		this.write(value + "\n");
	}

	private void writeError(String regExpKey) {
		regExpKey = "Fallo!!! Por tu error al aplicar defectuasamente el formato " + regExpKey;
		Console.output.println(regExpKey);
		Console.inputOutputLog.println(regExpKey);
	}

	public void write(Object object) {
		Console.output.print(object);
		Console.inputOutputLog.print(object);
	}

	public void writeln(Object object) {
		this.write(object + "\n");
	}

}
