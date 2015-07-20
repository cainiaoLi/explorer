/*
 * @author talent_marquis<Ìð²Ëºî¾ô>
 * Email: talent_marquis@163.com
 * Copyright (C) 2007 talent_marquis<Ìð²Ëºî¾ô>
 * All rights reserved.
 */
package display.dialog;

import util.DisplayUtil;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class AboutDialog extends Dialog
{
	private Text contentText;
	//private Text aboutText;
	private String version = "";
	private String author ="";
	private String email = "";
	private String content = "";
	private String other = "";
	private String title = "";
	//private String strAbout = JExplorerConstant.ABOUT_INFO + "\t" + JExplorerConstant.ABOUT_VERSION + "\n"
	//		+ JExplorerConstant.ABOUT_AUTHOR + "\n" + JExplorerConstant.ABOUT_EMAIL;

	public AboutDialog( Shell parent )
	{
		super( parent );
	}

	public void setTitle( String title)
	{
		this.title = title;
	}
	public void setVersion( String version )
	{
		this.version = version;
	}
	
	public void setAuthor(String author)
	{
		this.author = author;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public void setOtherInfo( String other )
	{
		this.other = other;
	}
	
	public void setContent( String content )
	{
		this.content = content;
	}
	
	public void open()
	{
		Shell parent = getParent();
		final Shell dialog = new Shell( parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL );
		dialog.setSize( 436, 290 );
		dialog.setText( title );
		DisplayUtil.showOnAtScreenMiddle( dialog );

		/*
		aboutText = new Text( dialog, SWT.READ_ONLY | SWT.WRAP );
		aboutText.setDoubleClickEnabled( false );
		aboutText.setEditable( false );
		aboutText.setText( strAbout );
		aboutText.setBounds( 131, 132, 175, 45 );
		*/
		final Button okBtn = new Button( dialog, SWT.NONE );
		okBtn.addSelectionListener( new SelectionAdapter()
		{
			public void widgetSelected( SelectionEvent e )
			{
				dialog.dispose();
			}
		} );
		okBtn.setBounds( 187, 224, 51, 25 );
		okBtn.setText( "OK" );

		contentText = new Text(dialog, SWT.V_SCROLL | SWT.READ_ONLY | SWT.BORDER | SWT.WRAP);
		contentText.setBounds(20, 83, 390, 135);
		contentText.setText( content );

		final Label titleLabel = new Label(dialog, SWT.NONE);
		titleLabel.setText("Version:");
		titleLabel.setBounds(20, 10, 37, 13);

		final Label authorLb = new Label(dialog, SWT.NONE);
		authorLb.setBounds(20, 29, 37, 13);
		authorLb.setText("Author:");

		final Label emailLb = new Label(dialog, SWT.NONE);
		emailLb.setBounds(20, 47, 37, 13);
		emailLb.setText("Email:");

		final Label otherLb = new Label(dialog, SWT.NONE);
		otherLb.setBounds(20, 64, 37, 13);
		otherLb.setText("Other:");

		final Label versionContentLb = new Label(dialog, SWT.NONE);
		versionContentLb.setBounds(63, 10, 347, 13);
		versionContentLb.setText( version );

		final Label authorContentLb = new Label(dialog, SWT.NONE);
		authorContentLb.setBounds(63, 29, 347, 13);
		authorContentLb.setText( author );

		final Label emailContentLb = new Label(dialog, SWT.NONE);
		emailContentLb.setBounds(63, 47, 347, 13);
		emailContentLb.setText( email );

		final Label otherContentLb = new Label(dialog, SWT.NONE);
		otherContentLb.setBounds(63, 64, 347, 13);
		otherContentLb.setText( other );

		final Link mailToLink = new Link(dialog, SWT.NONE);
		mailToLink.setBounds(271, 236, 125, 13);
		mailToLink.setText( "<a href=\"mailto:" + email + "\">"+ email + "</a>" );
		
		dialog.open();
		
		Display display = parent.getDisplay();
		while( !dialog.isDisposed() )
		{
			if( !display.readAndDispatch() )
				display.sleep();
		}
	}
}