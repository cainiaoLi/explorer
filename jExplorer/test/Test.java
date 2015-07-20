/*
 * @author talent_marquis<Ìð²Ëºî¾ô>
 * Email: talent_marquis@163.com
 * Copyright (C) 2007 talent_marquis<Ìð²Ëºî¾ô>
 * All rights reserved.
 */

public class Test
{
	private static boolean wildMatch( String pattern, String str )
	{
		pattern = toJavaPattern( pattern );
		return java.util.regex.Pattern.matches( pattern, str );
	}

	private static String toJavaPattern( String pattern )
	{
		String result = "^";
		char metachar[] =
		{ '$', '^', '[', ']', '(', ')', '{', '|', '*', '+', '?', '.', '\\' };
		for( int i = 0; i < pattern.length(); i++ )
		{
			char ch = pattern.charAt( i );
			boolean isMeta = false;
			for( int j = 0; j < metachar.length; j++ )
			{
				if( ch == metachar[ j ] )
				{
					result += "\\" + ch;
					isMeta = true;
					break;
				}
			}
			if( !isMeta )
			{
				if( ch == '*' )
				{
					result += ".*";
				}
				else
				{
					result += ch;
				}
			}
		}
		result += "$";
		System.out.println(result);
		return result;
	}

	public static void main( String[] args )
	{
		test( "*", "toto" );
		test( "toto.java", "tutu.java" );
		test( "12345", "1234" );
		test( "1234", "12345" );
		test( "*f", "" );
		test( "***", "toto" );
		test( "*.java", "toto." );
		test( "*.java", "toto.jav" );
		test( "*.java", "toto.java" );
		test( "abc*", "" );
		test( "a*c", "abbbbbccccc" );
		test( "abc*xyz", "abcxxxyz" );
		test( "*xyz", "abcxxxyz" );
		test( "abc**xyz", "abcxxxyz" );
		test( "abc**x", "abcxxx" );
		test( "*a*b*c**x", "aaabcxxx" );
		test( "abc*x*yz", "abcxxxyz" );
		test( "abc*x*yz*", "abcxxxyz" );
		test( "a*b*c*x*yf*z*", "aabbccxxxeeyffz" );
		test( "a*b*c*x*yf*zze", "aabbccxxxeeyffz" );
		test( "a*b*c*x*yf*ze", "aabbccxxxeeyffz" );
		test( "a*b*c*x*yf*ze", "aabbccxxxeeyfze" );
		test( "*LogServerInterface*.java", "_LogServerInterfaceImpl.java" );
		test( "abc*xyz", "abcxyxyz" );
	}

	private static void test( String pattern, String str )
	{
		System.out.println( pattern + " " + str + " =>> " + wildMatch( pattern, str ) );
	}

}
