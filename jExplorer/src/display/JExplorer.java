package display;

/*
 * @author talent_marquis<Ìð²Ëºî¾ô> Email: talent_marquis@163.com Copyright (C) 2007 talent_marquis<Ìð²Ëºî¾ô>
 * All rights reserved.
 */
import java.io.*;
import java.util.Map;
import java.util.List;

import logic.action.*;
import logic.filter.*;
import logic.provider.*;
import logic.sorter.*;

import org.eclipse.jface.action.*;
import org.eclipse.jface.viewers.*;
import org.eclipse.jface.window.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;
import swing2swt.layout.BorderLayout;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;

import common.JExplorerConstant;

import util.*;

public class JExplorer extends ApplicationWindow
{
	private List< String > sortTypeList = JExplorerConstant.getSortTypeList();

	private Combo fileTypeCmb;

	private Text fileNameText;

	private boolean sortStatus = true;
	private boolean isCut = false;

	private Combo diskCmb;

	private Tree tree;

	private Text pathText;

	private Table table;

	private TableViewer tbv;

	private TreeViewer tv;

	private AboutAction about_action;
	private CutAction cut_action;
	private CopyFileNamesToClipboardAction copy_action;
	private DeleteAction delete_action;
	private ExitAction exit_action;
	private OpenAction open_action;
	private PasteAction paste_action;
	private FilterAction fileter_action;
	private RefreshAction refresh_action;
	private RenameAction rename_action;
	private SelectAction select_all_action;
	private SelectAction select_file_action;
	private SelectAction select_folder_action;
	private UpAction up_action;
	private CreateNewFolderAction create_new_folder_action;

	private SortAction[] sortActions = new SortAction[ sortTypeList.size() ];

	public TableViewer getTablebViewer()
	{
		return tbv;
	}

	public TreeViewer getTreeViewer()
	{
		return tv;
	}

	private void initSortAction()
	{
		for( int i = 0; i < sortActions.length; i++ )
		{
			sortActions[ i ] = new SortAction( sortTypeList.get( i ), this );
		}
	}

	public JExplorer()
	{
		super( null );
		exit_action = new ExitAction( this );
		copy_action = new CopyFileNamesToClipboardAction( this );
		open_action = new OpenAction( this );
		delete_action = new DeleteAction( this );
		about_action = new AboutAction( this );
		up_action = new UpAction( this );
		fileter_action = new FilterAction( this );
		refresh_action = new RefreshAction( this );
		cut_action = new CutAction( this );
		paste_action = new PasteAction( this );
		rename_action = new RenameAction( this );
		select_all_action = new SelectAction( this, SelectAction.SELECT_TYPE_ALL );
		select_file_action = new SelectAction( this, SelectAction.SELECT_TYPE_FILE );
		select_folder_action = new SelectAction( this, SelectAction.SELECT_TYPE_FOLDER );
		create_new_folder_action = new CreateNewFolderAction( this );

		initSortAction();

		addStatusLine();
		addMenuBar();
		addToolBar( SWT.NONE );
	}

	private MenuManager createSortMenuManager()
	{
		MenuManager sort_manager = new MenuManager( "&Sort by" );
		for( SortAction sortAction : sortActions )
		{
			sort_manager.add( sortAction );
		}
		return sort_manager;

	}

	protected ToolBarManager createToolBarManager( int style )
	{
		ToolBarManager tool_bar_manager = new ToolBarManager();
		tool_bar_manager.add( exit_action );

		tool_bar_manager.add( new Separator() );
		tool_bar_manager.add( cut_action );
		tool_bar_manager.add( copy_action );
		tool_bar_manager.add( paste_action );
		tool_bar_manager.add( delete_action );
		tool_bar_manager.add( new Separator() );
		// tool_bar_manager.add( select_all_action );
		// tool_bar_manager.add( select_file_action );
		// tool_bar_manager.add( select_folder_action );
		// tool_bar_manager.add( new Separator() );
		tool_bar_manager.add( new Separator() );
		tool_bar_manager.add( rename_action );
		tool_bar_manager.add( create_new_folder_action );
		tool_bar_manager.add( new Separator() );
		tool_bar_manager.add( up_action );
		tool_bar_manager.add( open_action );
		tool_bar_manager.add( fileter_action );
		tool_bar_manager.add( refresh_action );
		tool_bar_manager.add( new Separator() );
		tool_bar_manager.add( about_action );
		// tool_bar_manager.add( createSortMenuManager() );
		return tool_bar_manager;
	}

	protected MenuManager createPopMenuManager()
	{
		MenuManager menu_manager = new MenuManager();
		menu_manager.add( open_action );
		menu_manager.add( up_action );
		menu_manager.add( new Separator() );
		menu_manager.add( cut_action );
		menu_manager.add( copy_action );
		menu_manager.add( paste_action );
		menu_manager.add( delete_action );
		menu_manager.add( new Separator() );
		menu_manager.add( select_all_action );
		menu_manager.add( select_file_action );
		menu_manager.add( select_folder_action );
		menu_manager.add( new Separator() );
		menu_manager.add( createSortMenuManager() );
		menu_manager.add( rename_action );
		menu_manager.add( create_new_folder_action );
		menu_manager.add( refresh_action );
		menu_manager.add( new Separator() );
		menu_manager.add( about_action );
		menu_manager.add( exit_action );
		return menu_manager;
	}

	protected MenuManager createMenuManager()
	{
		MenuManager bar_menu = new MenuManager( "" );
		MenuManager file_menu = new MenuManager( "&File" );
		MenuManager edit_menu = new MenuManager( "&Edit" );
		MenuManager view_menu = new MenuManager( "&View" );
		bar_menu.add( file_menu );
		bar_menu.add( edit_menu );
		bar_menu.add( view_menu );
		file_menu.add( exit_action );
		edit_menu.add( refresh_action );
		edit_menu.add( fileter_action );
		edit_menu.add( new Separator() );
		edit_menu.add( cut_action );
		edit_menu.add( copy_action );
		edit_menu.add( paste_action );
		edit_menu.add( delete_action );
		edit_menu.add( new Separator() );
		edit_menu.add( select_all_action );
		edit_menu.add( select_file_action );
		edit_menu.add( select_folder_action );
		edit_menu.add( new Separator() );
		edit_menu.add( createSortMenuManager() );
		edit_menu.add( rename_action );
		edit_menu.add( create_new_folder_action );
		edit_menu.add( up_action );
		edit_menu.add( open_action );
		view_menu.add( about_action );
		return bar_menu;

	}

	public IStructuredSelection getTableSelection()
	{
		return ( IStructuredSelection ) ( tbv.getSelection() );
	}

	private void initDiskCmb()
	{
		// ¼ì²éÕâ¸ö·½·¨£ºFile.listRoots();
		File[] roots = File.listRoots();
		for( File file : roots )
		{
			diskCmb.add( file.getPath() );
		}
		diskCmb.select( 0 );
		/*
		 * int AChar = 65; for( int i = 0; i < 26; i++ ) { String diskPath = ( char ) ( AChar + i ) +
		 * ":" + "\\"; File file = new File( diskPath ); if( file.isDirectory() ) { diskCmb.add(
		 * diskPath ); } diskCmb.select( 0 ); }
		 */
	}

	protected Control createContents( Composite parent )
	{

		SashForm sash_form = new SashForm( parent, SWT.HORIZONTAL | SWT.NULL );

		final Composite leftComposite = new Composite( sash_form, SWT.NONE );
		leftComposite.setLayout( new BorderLayout( 0, 0 ) );

		diskCmb = new Combo( leftComposite, SWT.READ_ONLY );
		diskCmb.setLayoutData( BorderLayout.NORTH );
		initDiskCmb();

		diskCmb.addSelectionListener( new SelectionAdapter()
		{
			public void widgetSelected( SelectionEvent e )
			{
				String str = diskCmb.getText();
				File file = new File( str );
				if( file.list() == null ) // it means root path contain nothing
				{
					tv.getTree().removeAll();
					tbv.getTable().removeAll();
					addFileTypeToCombo( file );
				}
				else if( file.isDirectory() )// && file.getParentFile() == null )
				{
					tv.setInput( file );
					tbv.setInput( file );
					addFileTypeToCombo( file );
					// tbv.refresh();
				}
				pathText.setText( StringUtil.deNull( file.getPath() ) );
			}
		} );

		diskCmb.addFocusListener( new FocusAdapter()
		{
			public void focusGained( FocusEvent e )
			{
				diskCmb.removeAll();
				initDiskCmb();
			}
		} );

		tv = new TreeViewer( leftComposite );

		tv.setContentProvider( new FileTreeContentProvider() );
		tv.setLabelProvider( new FileTreeLabelProvider() );
		tv.addFilter( new AllowOnlyFoldersFilter() );
		tv.setInput( new File( diskCmb.getText() ) );

		tree = tv.getTree();
		tree.setLayoutData( BorderLayout.CENTER );

		pathText = new Text( leftComposite, SWT.BORDER );
		pathText.setLayoutData( BorderLayout.SOUTH );
		pathText.setText( diskCmb.getText() );
		pathText.setToolTipText( "Press Enter go into the current path" );
		
		pathText.addKeyListener( new KeyAdapter()
		{
			public void keyReleased( KeyEvent e )
			{
				/*
				 * if( "".equals( pathText.getText() ) || pathText.getText() == null ) {
				 * tbv.resetFilters(); return; } else if( e.character == SWT.CR ) { // Enter
				 * tbv.resetFilters(); String str = pathText.getText(); tbv.addFilter( new
				 * FileNameFilter( str ) ); tbv.refresh(); }
				 */

				if( e.character == SWT.CR )
				{
					String str = pathText.getText();
					File file = new File( str );
					if( file.isDirectory() )
					{
						openFolder( file );
					}
				}

			}
		} );

		tv.addSelectionChangedListener( new ISelectionChangedListener()
		{
			public void selectionChanged( SelectionChangedEvent event )
			{
				IStructuredSelection selection = ( IStructuredSelection ) event.getSelection();
				Object selected_file = selection.getFirstElement();
				tv.expandToLevel( selected_file, 1 );
				tbv.setInput( selected_file );
				if( selected_file != null )
				{
					pathText.setText( StringUtil.deNull( ( ( File ) selected_file ).getPath() ) + File.separator );
				}
				else
				{
					pathText.setText( StringUtil.deNull( getCurrentRoot().getPath() ) );
				}
				pathText.setSelection( pathText.getText().length() );
				fileNameText.setText( "" );
				addFileTypeToCombo( ( File ) selected_file );

				doFilter();

				// String[] fileTypes = FileUtil.getFileTypesInFolder( ( File ) selected_file );
				// if( fileTypes != null )
				// {
				// fileTypeCmb.setItems( fileTypes );
				// }
				// else
				// {
				// fileTypeCmb.setItems( new String[]
				// { "" } );
				// }

				File folder = ( File ) selected_file;
				int fileNumber = FileUtil.getFileNumberInFolder( folder );
				int folderNumber = FileUtil.getFolderNumberInFolder( folder );
				String size = FileUtil.getFolderSize( folder );
				setStatus( folderNumber + " folders\t" + fileNumber + " files\t" + size );

			}
		} );

		// tv.addDoubleClickListener( new IDoubleClickListener()
		// {
		// public void doubleClick( DoubleClickEvent event )
		// {
		// IStructuredSelection selection = ( IStructuredSelection ) event.getSelection();
		// Object selected_file = selection.getFirstElement();
		//
		// if( selected_file instanceof File )
		// {
		// if( tv.getExpandedState( selected_file ) )
		// {
		// tv.collapseToLevel( selected_file, 1 );
		// }
		// else
		// {
		// tv.expandToLevel( selected_file, 1 );
		// }
		// }
		// }
		// } );

		tv.refresh();

		final Composite rightComposite = new Composite( sash_form, SWT.NONE );
		rightComposite.setLayout( new BorderLayout( 0, 0 ) );

		final Composite fileterComposite = new Composite( rightComposite, SWT.BORDER );
		fileterComposite.setLayout( new RowLayout() );
		fileterComposite.setLayoutData( BorderLayout.SOUTH );

		final Label nameLb = new Label( fileterComposite, SWT.NONE );
		nameLb.setText( "Name" );

		fileNameText = new Text( fileterComposite, SWT.BORDER );
		fileNameText.addKeyListener( new KeyAdapter()
		{
			public void keyReleased( KeyEvent e )
			{
				if( "".equals( fileNameText.getText() ) || fileNameText.getText() == null )
				{
					doFilter();
				}
				else if( e.character == SWT.CR )
				{
					// Enter
					doFilter();
				}
			}
		} );
		final RowData rd_fileNameText = new RowData();
		rd_fileNameText.height = 15;
		fileNameText.setLayoutData( rd_fileNameText );
		fileNameText.setToolTipText( "Press Enter to filter files by name" );

		final Label typeLb = new Label( fileterComposite, SWT.NONE );
		typeLb.setText( "Type" );

		fileTypeCmb = new Combo( fileterComposite, SWT.READ_ONLY );
		fileTypeCmb.addSelectionListener( fileter_action );
		addFileTypeToCombo( new File( diskCmb.getText() ) );
		fileTypeCmb.setToolTipText( "Filter files by type" );
		// fileTypeCmb.addSelectionListener( new SelectionAdapter()
		// {
		// public void widgetSelected( SelectionEvent e )
		// {
		// String fileType = fileTypeCmb.getText();
		// tbv.resetFilters();
		// tbv.addFilter( new FileTypeFilter( fileType ) );
		// }
		// } );

		final RowData rd_fileTypeCmb = new RowData();
		rd_fileTypeCmb.width = 40;
		rd_fileTypeCmb.height = 15;
		fileTypeCmb.setLayoutData( rd_fileTypeCmb );

		tbv = new TableViewer( rightComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI );
		tbv.setContentProvider( new FileTableContentProvider() );
		tbv.setLabelProvider( new FileTableLabelProvider() );
		tbv.setSorter( new FileSorter() );
		tbv.setInput( new File( diskCmb.getText() ) );

		tbv.addDoubleClickListener( open_action );
		tbv.addSelectionChangedListener( open_action );
		tbv.addSelectionChangedListener( delete_action );

		table = tbv.getTable();
		table.setLayout( new BorderLayout( 0, 0 ) );
		table.setLayoutData( BorderLayout.CENTER );
		table.setHeaderVisible( true );

		TableColumn[] columns = new TableColumn[ sortTypeList.size() ];
		for( int i = 0; i < columns.length; i++ )
		{
			TableColumn column = columns[ i ];
			String columnName = sortTypeList.get( i );
			column = new TableColumn( table, "name".equalsIgnoreCase( columnName ) ? SWT.LEFT : SWT.RIGHT );
			column.setWidth( 150 );
			column.setText( sortTypeList.get( i ) );
			final int index = i;
			column.addSelectionListener( new SelectionAdapter()
			{
				public void widgetSelected( SelectionEvent e )
				{
					Map< String, String > sortTypeMap = JExplorerConstant.getSortTypeMap();
					String sortTypeKey = sortTypeList.get( index );
					String sortTypeValue = sortTypeMap.get( sortTypeKey );
					doSort( sortTypeValue );
				}
			} );
		}

		// TableColumn column = new TableColumn( table, SWT.LEFT );
		// column.addSelectionListener( new SelectionAdapter()
		// {
		// public void widgetSelected( SelectionEvent e )
		// {
		// doSort( "name" );
		// }
		// } );
		// column.setText( "Name" );
		// column.setWidth( 200 );
		// column = new TableColumn( table, SWT.RIGHT );
		// column.addSelectionListener( new SelectionAdapter()
		// {
		// public void widgetSelected( SelectionEvent e )
		// {
		// doSort( "size" );
		// }
		// } );
		// column.setText( "Size" );
		// column.setWidth( 100 );
		// column = new TableColumn( table, SWT.RIGHT );
		// column.addSelectionListener( new SelectionAdapter()
		// {
		// public void widgetSelected( SelectionEvent e )
		// {
		// doSort( "type" );
		// }
		// } );
		// column.setText( "Type" );
		// column.setWidth( 100 );
		// column = new TableColumn( table, SWT.RIGHT );
		// column.addSelectionListener( new SelectionAdapter()
		// {
		// public void widgetSelected( SelectionEvent e )
		// {
		// doSort( "lastModified" );
		// }
		// } );
		// column.setText( "Last Modified" );
		// column.setWidth( 150 );
		// column = new TableColumn( table, SWT.RIGHT );
		// column.addSelectionListener( new SelectionAdapter()
		// {
		// public void widgetSelected( SelectionEvent e )
		// {
		// doSort( "status" );
		// }
		// } );
		// column.setText( "Status" );
		// column.setWidth( 100 );
		// // column = new TableColumn( tbv.getTable(), SWT.LEFT );
		// // column.setText( "Path" );
		// // column.setWidth( 300 );

		tbv.addSelectionChangedListener( new ISelectionChangedListener()
		{
			public void selectionChanged( SelectionChangedEvent event )
			{
				IStructuredSelection selection = ( IStructuredSelection ) event.getSelection();
				if( selection.size() == 0 )
				{
					// File folder = ( File ) ( ( IStructuredSelection )
					// tv.getSelection() ).getFirstElement();
					return;
				}
				else if( selection.size() == 1 )
				{
					File file = ( File ) selection.getFirstElement();
					if( file.isFile() )
					{
						setStatus( FileUtil.getFileInfo( ( File ) selection.getFirstElement() ) );
					}
					else
					{
						File folder = ( File ) selection.getFirstElement();
						int fileNumber = FileUtil.getFileNumberInFolder( folder );
						int folderNumber = FileUtil.getFolderNumberInFolder( folder );
						String size = FileUtil.getFolderSize( folder );
						setStatus( folderNumber + " folders\t" + fileNumber + " files\t" + size );
					}
					return;
				}
				else
				{
					String strNumber = "Number of files selected is " + selection.size();
					String strSize = "Size of files selected is " + FileUtil.getFilesSize( selection.toArray() );
					setStatus( strNumber + "\t" + strSize );
					return;
				}
			}
		} );

		// tbv.addDoubleClickListener( new IDoubleClickListener()
		// {
		// public void doubleClick( DoubleClickEvent event )
		// {
		// IStructuredSelection selection = ( IStructuredSelection )
		// event.getSelection();
		// Object selected_file = selection.getFirstElement();
		//
		// if( ( ( File ) selected_file ).isDirectory() )
		// {
		// // tv.setSelection( selection );
		// // tv.setExpandedState( selected_file, true );
		// // tbv.setInput( selected_file );
		// openFolder( ( File ) selected_file );
		// }
		// else
		// {
		// Program.launch( ( ( File ) selected_file ).getAbsolutePath() );
		// }
		// }
		// } );
		MenuManager pop_menu_manager = createPopMenuManager();
		tbv.getTable().setMenu( pop_menu_manager.createContextMenu( tbv.getTable() ) );
		tbv.refresh();
		sash_form.setWeights( new int[]
		{ 1, 4 } );
		return sash_form;

	}

	public static void main( String[] args )
	{
		JExplorer w = new JExplorer();
		w.setBlockOnOpen( true );
		w.open();
		// Display.getCurrent().dispose();
		JExplorerUtil.getClipboard().dispose();

	}

	protected void configureShell( Shell newShell )
	{
		super.configureShell( newShell );
		newShell.setText( JExplorerConstant.TITLE + " " + JExplorerConstant.ABOUT_VERSION );
		newShell.setImage( JExplorerUtil.getImageRegistry().get( "title" ) );
	}

	public void openFolder( File folder )
	{
		tv.setExpandedState( folder, true );
		tv.setSelection( new StructuredSelection( folder ), true );
	}

	public void addFileTypeToCombo( File folder )
	{
		String[] fileTypes = FileUtil.getFileTypesInFolder( folder );
		if( fileTypes != null )
		{
			fileTypeCmb.setItems( fileTypes );
		}
		else
		{
			fileTypeCmb.setItems( new String[]
			{ "" } );
		}
	}

	public void doSort( String sortTypeValue )
	{
		sortStatus = !sortStatus;

		Map< String, Integer > sortMap = JExplorerConstant.getSortMap();

		int column = sortMap.get( sortTypeValue );
		column = sortStatus ? -column : column;

		FileSorter fileSorter = ( FileSorter ) tbv.getSorter();
		fileSorter.doSort( column );
		tbv.refresh();

	}

	public void doFileter( String fileName, String fileType )
	{
		tbv.resetFilters();
		tbv.addFilter( new FileNameFilter( fileName ) );
		tbv.addFilter( new FileTypeFilter( fileType ) );
		tbv.refresh();
	}

	public void doFilter()
	{
		String name = fileNameText.getText();
		String type = fileTypeCmb.getText();
		doFileter( name, type );
	}

	public void refresh()
	{
		tbv.refresh();
		tv.refresh();
		// initDiskCmb();
	}

	public boolean isCut()
	{
		return isCut;
	}

	public void setCut( boolean isCut )
	{
		this.isCut = isCut;
	}

	public File getCurrentRoot()
	{
		return new File( diskCmb.getText() );
	}

}
