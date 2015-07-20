/*
 * @author talent_marquis<Ìð²Ëºî¾ô> Email: talent_marquis@163.com Copyright (C) 2007 talent_marquis<Ìð²Ëºî¾ô>
 * All rights reserved.
 */
package util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class FileUtil
{
	// FileUtil constant
	public static final String[] FILE_SIZE_UNIT =
	{ "Byte", "KB", "MB", "GB", "TB", "PB" };

	public static final String FILE_TYPE_FOLDER = "dir";

	public static final String FILE_TYPE_ALL = "*.*";

	public static final String FILE_STATUS_HIDDEN = "H";

	public static final String FILE_STATUS_CAN_READ = "R";

	public static final String FILE_STATUS_CAN_WRITE = "W";

	public static final String FILE_STATUS_IS_FILE = "F";

	public static final String FILE_STATUS_IS_FOLDER = "D";

	/**
	 * 
	 * @param file
	 * @param format
	 *            the date format like "yyyy-MM-dd"
	 * @return the last modified date of the file
	 */
	public static String getFileLastModified( File file, String format )
	{
		Date date = new Date( file.lastModified() );
		SimpleDateFormat sdf = new SimpleDateFormat( format );
		return sdf.format( date ).toString();
	}

	/**
	 * 
	 * @param folder
	 * @return the files' number in folder
	 */
	public static int getFileNumberInFolder( File folder )
	{
		if( folder == null )
			return 0;
		if( folder.isFile() )
			return 0;

		File[] files = folder.listFiles();
		if( files == null )
			return 0;
		int count = 0;
		for( File file : files )
		{
			if( file.isFile() )
			{
				count++;
			}
		}
		return count;
	}

	/**
	 * 
	 * @param folder
	 * @return the folder's number in folder
	 */
	public static int getFolderNumberInFolder( File folder )
	{
		if( folder == null )
			return 0;
		if( folder.isFile() )
			return 0;

		File[] files = folder.listFiles();
		if( files == null )
			return 0;
		int count = 0;
		for( File file : files )
		{
			if( file.isDirectory() )
			{
				count++;
			}
		}
		return count;
	}

	/**
	 * 
	 * @param file
	 * @return the last modified date of the file with the default format : yyyy-MM-dd HH:mm:ss
	 */
	public static String getFileLastModified( File file )
	{
		return getFileLastModified( file, "yyyy-MM-dd HH:mm:ss" );
	}

	/**
	 * 
	 * @param file
	 * @return file name without type<br>
	 *         for example:<br>
	 *         the file name is "example.txt" then the return sub string is "example"
	 */
	public static String getFileName( File file )
	{
		if( file.isDirectory() )
		{
			return file.getName();
		}
		else
		{
			String fileType = getFileType( file );
			if( "unknown".equals( fileType ) )
			{
				return file.getName();
			}
			else
			{
				String fileName = file.getName();
				// "-1" in order to drop the "."
				return fileName.substring( 0, fileName.length() - fileType.length() - 1 );
			}
		}

	}

	/**
	 * 
	 * @param file
	 * @return the file type like "exe"
	 */
	public static String getFileType( File file )
	{
		if( file.isDirectory() )
		{
			return FILE_TYPE_FOLDER;
		}
		else
		{
			String[] strArray = file.getName().split( "\\." );

			int length = strArray.length;
			if( length > 1 )
			{
				return strArray[ length - 1 ];

			}
			else
			{
				return "unknown";
			}
		}
	}

	public static void main( String args[] )
	{
		// String[] s = getFileTypesInFolder( new File( "c:\\" ) );
		// System.out.println();
	}

	/**
	 * 
	 * @param folder
	 * @return the types of file in this folder
	 */
	public static String[] getFileTypesInFolder( File folder )
	{
		if( folder == null )
		{
			return null;
		}
		if( folder.isFile() )
		{
			return null;
		}
		Set< String > fileTypes = new TreeSet< String >();
		fileTypes.add( FILE_TYPE_ALL );
		File[] files = folder.listFiles();
		if( files == null )
		{
			return null;
		}

		for( File file : files )
		{
			fileTypes.add( getFileType( file ).toLowerCase() );
		}
		return convertObjectArrayToStringArray( fileTypes.toArray() );
	}

	/**
	 * 
	 * @param objectArray
	 * @return stringArray
	 */
	private static String[] convertObjectArrayToStringArray( Object[] objectArray )
	{
		String[] stringArray = new String[ objectArray.length ];
		int i = 0;
		for( Object o : objectArray )
		{
			stringArray[ i ] = o.toString();
			i++;
		}
		// Arrays.sort( stringArray );
		return stringArray;
	}

	/**
	 * 
	 * @param folder
	 * @return the size of files in the folder, don't include the size of sub-folders
	 */
	public static String getFolderSize( File folder )
	{
		if( folder == null )
			return "";

		if( folder.isDirectory() )
		{
			return getFilesSize( folder.listFiles() );
		}
		else
		{
			return "";
		}
	}

	/**
	 * 
	 * @param file
	 * @return files Status: H-hidden;R-Read;W-Write
	 */
	public static String getFileStatus( File file )
	{
		StringBuffer buffer = new StringBuffer();

		buffer.append( file.isHidden() ? FILE_STATUS_HIDDEN + "\t" : "\t" );
		buffer.append( file.canRead() ? FILE_STATUS_CAN_READ + "\t" : "\t" );
		buffer.append( file.canWrite() ? FILE_STATUS_CAN_WRITE + "\t" : "\t" );

		return buffer.toString();
	}

	/**
	 * 
	 * @param file
	 * @return file's info: full path, lastModified, status, file or folder, size
	 */
	public static String getFileInfo( File file )
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append( file.getPath() );
		buffer.append( "\t" );
		buffer.append( getFileLastModified( file ) );
		buffer.append( "\t" );
		buffer.append( getFileStatus( file ) );
		buffer.append( "\t" );
		buffer.append( file.isFile() ? FILE_STATUS_IS_FILE : FILE_STATUS_IS_FOLDER );
		buffer.append( "\t" );
		buffer.append( file.isFile() ? file.length() : "" );

		return buffer.toString();
	}

	/**
	 * 
	 * @param files
	 *            File Array
	 * @return the size of files in the file array
	 */
	public static String getFilesSize( Object[] files )
	{
		if( files == null )
			return "";
		double filesSize = 0;
		for( Object file : files )
		{
			if( ( ( File ) file ).isFile() )
			{
				filesSize += ( ( File ) file ).length();
			}
		}
		return getFormatSize( filesSize );
	}

	/**
	 * 
	 * @param file
	 * @return the size of file
	 */
	public static String getFileSize( File file )
	{
		return getFormatSize( file.length() );
	}

	/**
	 * 
	 * @param folder
	 *            the folder you want to check
	 * @param type
	 *            the file type you want to filter
	 * @return
	 */
	public static List< File > getFileList( File folder, String type )
	{
		List< File > fileNameList = new ArrayList< File >();
		File[] files = folder.listFiles();
		for( File file : files )
		{
			if( getFileType( file ).equalsIgnoreCase( type ) )
			{
				fileNameList.add( file );
			}
		}
		return fileNameList;
	}

	public static boolean isFileExist( File file, File folder )
	{
		File[] files = folder.listFiles();
		String fileName = file.getName();
		for( File f : files )
		{
			if( fileName.equals( f.getName() ) )
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * get the content of the file
	 * 
	 * @param file
	 *            target file
	 * @param bufferSize
	 *            the buffer size
	 * @return String the file content
	 * @throws FileNotFoundException,
	 *             IOException
	 */
	public static String getFileContent( File file, int bufferSize ) throws FileNotFoundException, IOException
	{

		StringBuffer strBuffer = new StringBuffer();
		//
		// FileChannel in = new FileInputStream( file ).getChannel();
		//
		// ByteBuffer buffer = ByteBuffer.allocate( bufferSize );
		// while( in.read( buffer ) != -1 )
		// {
		// buffer.flip();
		// String temp = new String( buffer.array(), "gbk" );
		// strBuffer.append( temp );
		// buffer.clear();
		// }
		// return strBuffer.toString();

		BufferedReader in = new BufferedReader( new FileReader( file ), bufferSize );

		// char[] buffer = new char[ bufferSize ];

		String tempStr = in.readLine();
		strBuffer.append( tempStr );
		while( ( tempStr = in.readLine() ) != null )
		{
			strBuffer.append( "\n" ).append( tempStr );
		}
		in.close();
		return strBuffer.toString();
	}

	/**
	 * get the content of the file with defalut buffer size 1024
	 * 
	 * @param file
	 *            target file
	 * @return String the file content
	 * @throws FileNotFoundException,
	 *             IOException
	 */
	public static String getFileContent( File file ) throws FileNotFoundException, IOException
	{
		return getFileContent( file, 1024 );
	}

	/**
	 * paste a file to the target folder with the default buffer size 4096
	 * 
	 * @param source_file
	 *            the source file
	 * @param target_folder
	 *            the target folder
	 * @return
	 * @throws FileNotFoundException
	 */
	public static void doPaste( File source_file, File target_folder ) throws FileNotFoundException, IOException
	{
		doPaste( source_file, target_folder, 4096 );
	}

	/**
	 * paste a file to the target folder
	 * 
	 * @param source_file
	 *            the source file
	 * @param target_folder
	 *            the target folder
	 * @param bufferSize
	 *            the buffer size
	 * @return
	 * @throws FileNotFoundException
	 */
	public static void doPaste( File source_file, File target_folder, int bufferSize ) throws FileNotFoundException,
			IOException
	{
		String path = target_folder.getAbsolutePath();
		String name = source_file.getName();
		File target_file = new File( path + File.separator + name );

		FileChannel in = new FileInputStream( source_file ).getChannel();
		FileChannel out = new FileOutputStream( target_file ).getChannel();

		// copy source file to target folder directly
		in.transferTo( 0, in.size(), out );

		/*
		 * ByteBuffer buffer = ByteBuffer.allocate( bufferSize ); while( in.read(buffer) != -1 ) {
		 * buffer.flip(); out.write( buffer ); buffer.clear(); }
		 */
		in.close();
		out.close();
	}

	/**
	 * 
	 * @param size
	 * @return the formatted size
	 */
	private static String getFormatSize( double size )
	{
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits( 2 );
		int i = 0;
		String[] unit = FILE_SIZE_UNIT;
		for( i = 0; i < unit.length; i++ )
		{
			if( ( long ) ( size / 1024 ) > 0 )
			{
				size /= 1024;
			}
			else
			{
				break;
			}
		}
		return nf.format( size ) + unit[ i ];
	}
}
