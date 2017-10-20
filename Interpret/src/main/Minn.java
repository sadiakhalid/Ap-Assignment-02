package main;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Minn {
	static HashMap<String,Boolean> bools=new HashMap<String,Boolean>();
	static HashMap<String,Integer> ints=new HashMap<String,Integer>();
	static HashMap<String,String> strings=new HashMap<String,String>();
	
	
	
	
	
	static String inte = "(\\d+)";
	static String bool = "(tt|ff)";
	static String alpha = "(\"(.+)?\")";
	static String id = "([a-z]([\\w\\d]+)?)";
	static String valueK = "(" + inte + "|" + bool + "|" + alpha + ")";
	static String Type = "(int|bool|alpha)";
	static String bop = "(\\+|\\*|and|or|\\/|\\^|=|==|><|>|<)";
	static String uop = "(-|not)";
	static String subexp = "((" + valueK + ")" + "|(" + id + "))";// temp
	static String expE = "(" + "(" + subexp + bop + subexp + "(" + bop + subexp + ")?)" + "|" + "(" + uop + subexp + ")"
			+ "|" + "(" + subexp + ")" + ");?";
	static String Dec = "(" + "(" + "const " + id + ":" + Type + "=" + expE + ")" + "|" + "(" + "var " + id + ":" + Type
			+ "=" + expE + ")" + "|" + "(" + "var " + id + ":" + Type + ";?)" + ")";
	static String C0 = "((skip)|(" + id + "=" + expE + ")" + ")";
	static String C1 = "((skip)|(" + id + "=" + expE + ")" + ")";
	static String While = "(while " + expE + " do " + expE + ")";
	static String If = "(if " + expE + " then " + C0 + " else " + C1 + ")";
	static String Con = "((" + C0 + ")|(" + If + ")|(" + While + "))";
	static String Print = "(print " + expE + ")";

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		

		BufferedReader file = new BufferedReader(new FileReader("Interpreter.txt"));
		String value = null;
		int c = 1;

		while ((value = file.readLine()) != null && value.length() != 0) {

			System.out.print(value + "		");

			if (value.length() == 0)
				System.out.println("ok so far");
			else if (value.split(Dec).length == 0)// 
			{
				System.out.println("ok dec");
				//interpret(value);
			} else if (value.split(expE).length == 0) {
				System.out.println("ok exp");
				//interpret(value);
			}
			else if(value.split("while ")[0].length()==0)
			{
				System.out.println("ok while");
			}else if(value.split("print ")[0].length()==0)
			{
				System.out.println("ok print");
				
			}else if(value.split("if ")[0].length()==0)
			{
				System.out.println("ok if");
				
			}
			else {
				System.out.println("Error on line:" + c);
			}
			c++;
		}

	}

	private static void interpret(String value) {
		// TODO Auto-generated method stub
		String name = null;
		String val = null;
		if (value.split("(var |const )")[0].length() == 0) {//dec
			name = value.split("(var |const )")[1].split(":")[0];
			val = value.split("=")[1].split(";?")[0];
			
			
		} else if (value.split("(while )")[0].length() == 0) {
			name = value.split("=")[0];
			val = value.split("=")[1].split(";?")[0];
		} else if (value.split("(if )")[0].length() == 0) {
			name = value.split("=")[0];
			val = value.split("=")[1].split(";?")[0];
		} else if (value.split("(print )")[0].length() == 0) {
			name = value.split("=")[0];
			val = value.split("=")[1].split(";?")[0];
		} else {//exp x=y+1; x=3;
			name = value.split("=")[0];
			val = value.split("=")[1].split(";?")[0];
		}
		System.out.println(name);
	}

}
