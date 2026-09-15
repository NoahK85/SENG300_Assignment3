/*
 * File: libraryFrame.java
 * Author: Chris Grimm & Noah Kabelman
 * Date: 9/14/26
 * Institution: Ferris State University
 * SENG300: Software Data Structures and Algorithms
 * Assignment 3
 * Description: UI for the library app.
*/

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.border.BevelBorder;
import java.awt.BorderLayout;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class LibraryFrame extends JFrame implements ActionListener, ListSelectionListener {

	private static final long serialVersionUID = 1L;
	
	//Components
	JPanel        contentPane;
	JTextField    searchText;
	JTextField    authorText;
	JTextField    dateText;
	JTextField    languageText;
	JTextField    idText;
	JTextField    isbnText;
	JPanel        titlePanel;
	JLabel        titleLabelIcon;
	JLabel        titleLabel;
	JPanel	      lowerPanel;
	JPanel	      libraryPanel;
	JPanel        searchPanel;
	JLabel 	      searchLabel;
	JLabel 	      sortLabel;
	JComboBox     sortComboBox;
	JPanel 	      sortPanel;
	JComboBox     orderComboBox;
	JPanel        listPanel;
	JList<String> list;
	JPanel		  infoPanel;
	JScrollPane   scrollPane;
	JPanel        infoTitlePanel;
	JLabel        bookTitleLabel;
	JPanel 		  descPanel;
	JPanel 		  imagePanel;
	JLabel 		  bookImage;
	JPanel 		  bookDescriptionPanel;
	JPanel 		  authorPanel;
	JLabel 		  authorLabel;
	JPanel 		  datePanel;
	JLabel   	  dateLabel;
	JPanel 		  languagePanel;
	JLabel 		  languageLabel;
	JPanel 		  idPanel;
	JLabel 		  idLabel;
	JPanel 		  isbnPanel;
	JLabel 		  lblIsbn;
	JPanel 		  ratingPanel;
	JLabel 		  star1;
	JLabel 		  star2;
	JLabel 		  star3;
	JLabel 		  star4;
	JLabel 		  star5;
	JPanel 		  buttonsPanel;
	JButton 	  editButton;
	JButton		  deleteButton;
	
	//variables
	ImageIcon starIcon      = new ImageIcon("Graphics\\star.png");
	ImageIcon halfStarIcon  = new ImageIcon("Graphics\\starHalf.png");
	ImageIcon emptyStarIcon = new ImageIcon("Graphics\\emptyStar.png");
	ImageIcon editIcon      = new ImageIcon("Graphics\\edit.png");
	ImageIcon checkIcon     = new ImageIcon("Graphics\\check.png");
	ImageIcon recycleIcon   = new ImageIcon("Graphics\\recycle.png");
	ImageIcon xIcon         = new ImageIcon("Graphics\\x.png");
	boolean triggerUpdate   = true;  //prevents valueChanged event from triggering early
	boolean editMode        = false; //controls whether edit mode is active or not
	List_Functions bookList;
	
	//functions
	//updates list component's contents
	public void updateList() {
		triggerUpdate = false;
		ArrayList<String> firstTen = bookList.getFirstTen();
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (int i = 0; i < firstTen.size(); i++) {
			listModel.add(i, firstTen.get(i));
		}
		list.setModel(listModel);
		list.setSelectedIndex(0);
		triggerUpdate = true;
	}
	
	//updates the book description section
	public void updateDescription() throws IOException {
		int selectedIndex = list.getSelectedIndex();
		if (ifIndexValid(selectedIndex)) { //normal behavior
			Book book = bookList.getBook(selectedIndex);
			bookTitleLabel.setText(book.getTitle());
			authorText    .setText(book.getAuthor());
			dateText      .setText(book.getOriginalPublicationYear() + "");
			languageText  .setText(book.getLanguage());
			idText        .setText(book.getId() + "");
			isbnText      .setText(book.getIsbn());
			bookImage     .setIcon(new ImageIcon(ImageIO.read(book.getImageUrl())));
			updateStarRating(book.getRating());
		}
		else { //failsafe
			bookTitleLabel.setText("No Book Found");
			authorText    .setText("");
			dateText      .setText("");
			languageText  .setText("");
			idText        .setText("");
			isbnText      .setText("");
			bookImage     .setIcon(null);
			updateStarRating(0);
		}
	}
	
	//updates star rating panel
	public void updateStarRating(double rating) {
		final JLabel[] STARS = {star1, star2, star3, star4, star5};
		for (int i = 0; i < 5; i++) {
			final double STAR_RATING = rating - i;
			if (STAR_RATING <= .25) {  //star is less than .25
				STARS[i].setIcon(emptyStarIcon); //set star to empty
			}
			else if (STAR_RATING <= .75) { //star is .25 to .75
				STARS[i].setIcon(halfStarIcon); //set star to half
			}
			else {
				STARS[i].setIcon(starIcon);    //set star to full
			}
		}
	}
	
	//delete button pressed
	public void deleteButtonPress() throws IOException {
		if (editMode == false) {
			int selectedIndex = list.getSelectedIndex();
			if (ifIndexValid(selectedIndex)) { //normal behavior
				bookList.deleteList(selectedIndex);
				updateList();
				updateDescription();
			}
		}
		else {
			editMode = false;
			editButton.setIcon(editIcon);
			editButton.setText("Edit");
			deleteButton.setIcon(recycleIcon);
			deleteButton.setText("Delete");
			authorText  .setEditable(editMode);
			dateText    .setEditable(editMode);
			languageText.setEditable(editMode);
			idText      .setEditable(editMode);
			isbnText    .setEditable(editMode);
			updateDescription();
		}
	}
	
	//edit button pressed
	public void editButtonPress() throws IOException {
		if (editMode == false) {
			editMode = true;
			editButton.setIcon(checkIcon);
			editButton.setText("Confirm");
			deleteButton.setIcon(xIcon);
			deleteButton.setText("Cancel");
		}
		else {
			editMode = false;
			editButton.setIcon(editIcon);
			editButton.setText("Edit");
			deleteButton.setIcon(recycleIcon);
			deleteButton.setText("Delete");
			
			int ind = list.getSelectedIndex();
			//check if edits are valid
			if (ifIndexValid(ind)) {
				if (ifEditsValid()) {
					//edit array
					//we can replace this with editList, but I believe this is technically
					//more efficient
					Book editBook = bookList.getBook(ind);
					editBook.setAuthor(authorText.getText());
					editBook.setOriginalPublicationYear(Integer.parseInt(dateText.getText()));
					editBook.setLanguage(languageText.getText());
					editBook.setId(Integer.parseInt(idText.getText()));
					editBook.setIsbn(isbnText.getText());
				}
			}
			
			updateList();
			updateDescription();
		}
		authorText  .setEditable(editMode);
		dateText    .setEditable(editMode);
		languageText.setEditable(editMode);
		idText      .setEditable(editMode);
		isbnText    .setEditable(editMode);
	}
	
	//check if selected index is valid
	public boolean ifIndexValid(int selectedIndex) {
		if (selectedIndex < bookList.getSize() && selectedIndex > -1) {
			return true;
		}
		return false;
	}
	
	//check if edits are valid
	public boolean ifEditsValid() {
		if (dateText.getText().matches("[0-9]*")) {
			if (idText.getText().matches("[0-9]*")) {
				if (isbnText.getText().matches("[0-9]*(X)?")) {
					return true;
				}
			}
		}
		JOptionPane.showMessageDialog(null,"Invalid Edit!");
		return false;
	}
	
	/**
	 * Create the frame.
	 */
	public LibraryFrame(List_Functions bookLists) throws IOException {
		bookList = bookLists;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("Graphics\\digitalLibrary.png"));
		setTitle("Desktop Library");
		setFont(new Font("8-bit Operator+ 8", Font.BOLD, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 609, 431);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(206, 0, 0));
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, new Color(255, 255, 255), new Color(128, 128, 128), Color.BLACK));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		titlePanel = new JPanel();
		titlePanel.setBorder(new EmptyBorder(0, 10, 0, 0));
		titlePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		titlePanel.setBackground(Color.BLUE);
		titlePanel.setPreferredSize(new Dimension(10, 40));
		contentPane.add(titlePanel, BorderLayout.NORTH);
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
		
		titleLabelIcon = new JLabel("");
		titleLabelIcon.setIcon(new ImageIcon("Graphics\\digitalLibrary.png"));
		titlePanel.add(titleLabelIcon);
		
		titleLabel = new JLabel("Desktop Library");
		titleLabel.setMinimumSize(new Dimension(72, 5));
		titleLabel.setPreferredSize(new Dimension(72, 6));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		titleLabel.setIconTextGap(5);
		titleLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBackground(Color.WHITE);
		titleLabel.setFont(new Font("8-bit Operator+", Font.BOLD, 30));
		titlePanel.add(titleLabel);
		
		lowerPanel = new JPanel();
		lowerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		lowerPanel.setBackground(Color.LIGHT_GRAY);
		contentPane.add(lowerPanel, BorderLayout.CENTER);
		lowerPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		libraryPanel = new JPanel();
		libraryPanel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.BLACK, Color.BLACK, Color.DARK_GRAY));
		libraryPanel.setBackground(Color.LIGHT_GRAY);
		lowerPanel.add(libraryPanel);
		GridBagLayout gbl_libraryPanel = new GridBagLayout();
		gbl_libraryPanel.columnWeights = new double[]{1.0};
		gbl_libraryPanel.rowWeights = new double[]{0.0, 0.0, 1.0};
		libraryPanel.setLayout(gbl_libraryPanel);
		
		searchPanel = new JPanel();
		searchPanel.setBorder(new CompoundBorder(new EmptyBorder(1, 5, 1, 4), new LineBorder(new Color(0, 0, 0))));
		searchPanel.setBackground(Color.LIGHT_GRAY);
		searchPanel.setSize(new Dimension(0, 60));
		searchPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		searchPanel.setMaximumSize(new Dimension(32767, 40));
		searchPanel.setPreferredSize(new Dimension(0, 40));
		GridBagConstraints gbc_searchPanel = new GridBagConstraints();
		gbc_searchPanel.insets = new Insets(0, 0, 5, 0);
		gbc_searchPanel.anchor = GridBagConstraints.NORTH;
		gbc_searchPanel.gridwidth = 0;
		gbc_searchPanel.fill = GridBagConstraints.BOTH;
		gbc_searchPanel.gridx = 0;
		gbc_searchPanel.gridy = 0;
		libraryPanel.add(searchPanel, gbc_searchPanel);
		
		searchLabel = new JLabel("Search:");
		searchLabel.setIcon(new ImageIcon("Graphics\\search.png"));
		searchLabel.setFont(new Font("8-bit Operator+", Font.BOLD, 13));
		searchPanel.add(searchLabel);
		
		searchText = new JTextField();
		searchText.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		searchText.setPreferredSize(new Dimension(30, 23));
		searchText.setText("ID/ISBN");
		searchText.setFont(new Font("8-bit Operator+", Font.PLAIN, 10));
		searchText.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK));
		searchPanel.add(searchText);
		searchText.setColumns(10);
		
		sortPanel = new JPanel();
		sortPanel.setBorder(new CompoundBorder(new EmptyBorder(1, 5, 1, 4), new LineBorder(new Color(0, 0, 0))));
		sortPanel.setBackground(Color.LIGHT_GRAY);
		sortPanel.setPreferredSize(new Dimension(0, 40));
		sortPanel.setSize(new Dimension(0, 50));
		sortPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		GridBagConstraints gbc_sortPanel = new GridBagConstraints();
		gbc_sortPanel.insets = new Insets(0, 0, 5, 0);
		gbc_sortPanel.fill = GridBagConstraints.BOTH;
		gbc_sortPanel.gridx = 0;
		gbc_sortPanel.gridy = 1;
		libraryPanel.add(sortPanel, gbc_sortPanel);
		
		sortLabel = new JLabel("Sort:");
		sortLabel.setIcon(new ImageIcon("Graphics\\filter.png"));
		sortLabel.setFont(new Font("8-bit Operator+", Font.BOLD, 13));
		sortPanel.add(sortLabel);
		
		sortComboBox = new JComboBox();
		sortComboBox.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK));
		sortComboBox.setModel(new DefaultComboBoxModel(new String[] {"Book ID", "ISBN", "Authors", "Publication Date"}));
		sortComboBox.setPreferredSize(new Dimension(100, 21));
		sortComboBox.setFont(new Font("8-bit Operator+", Font.PLAIN, 10));
		sortPanel.add(sortComboBox);
		
		orderComboBox = new JComboBox();
		orderComboBox.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK));
		orderComboBox.setPreferredSize(new Dimension(70, 21));
		orderComboBox.setFont(new Font("8-bit Operator+", Font.BOLD, 10));
		orderComboBox.setModel(new DefaultComboBoxModel(new String[] {"ASC", "DESC"}));
		sortPanel.add(orderComboBox);
		
		listPanel = new JPanel();
		listPanel.setPreferredSize(new Dimension(0, 0));
		listPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		listPanel.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_listPanel = new GridBagConstraints();
		gbc_listPanel.insets = new Insets(0, 0, 5, 0);
		gbc_listPanel.fill = GridBagConstraints.BOTH;
		gbc_listPanel.gridx = 0;
		gbc_listPanel.gridy = 2;
		libraryPanel.add(listPanel, gbc_listPanel);
		listPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK));
		listPanel.add(scrollPane);
		
		list = new JList<String>();
		list.setFont(new Font("8-bit Operator+", Font.BOLD, 12));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		updateList();
		list.addListSelectionListener(this);

		scrollPane.setViewportView(list);
		
		infoPanel = new JPanel();
		infoPanel.setBackground(Color.LIGHT_GRAY);
		infoPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		lowerPanel.add(infoPanel);
		GridBagLayout gbl_infoPanel = new GridBagLayout();
		gbl_infoPanel.columnWidths = new int[]{209, 0};
		gbl_infoPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_infoPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		infoPanel.setLayout(gbl_infoPanel);
		
		infoTitlePanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) infoTitlePanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		infoTitlePanel.setBackground(Color.LIGHT_GRAY);
		infoTitlePanel.setName("");
		infoTitlePanel.setPreferredSize(new Dimension(10, 40));
		infoTitlePanel.setAlignmentY(Component.TOP_ALIGNMENT);
		GridBagConstraints gbc_infoTitlePanel = new GridBagConstraints();
		gbc_infoTitlePanel.weightx = 1.0;
		gbc_infoTitlePanel.fill = GridBagConstraints.BOTH;
		gbc_infoTitlePanel.gridx = 0;
		gbc_infoTitlePanel.gridy = 0;
		infoPanel.add(infoTitlePanel, gbc_infoTitlePanel);
		
		bookTitleLabel = new JLabel("(Book Title)");
		bookTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bookTitleLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		bookTitleLabel.setFont(new Font("8-bit Operator+", Font.BOLD, 20));
		infoTitlePanel.add(bookTitleLabel);
		
		descPanel = new JPanel();
		descPanel.setBackground(Color.LIGHT_GRAY);
		descPanel.setPreferredSize(new Dimension(10, 0));
		GridBagConstraints gbc_descPanel = new GridBagConstraints();
		gbc_descPanel.weightx = 1.0;
		gbc_descPanel.weighty = 1.0;
		gbc_descPanel.fill = GridBagConstraints.BOTH;
		gbc_descPanel.insets = new Insets(0, 0, 5, 0);
		gbc_descPanel.gridx = 0;
		gbc_descPanel.gridy = 1;
		infoPanel.add(descPanel, gbc_descPanel);
		GridBagLayout gbl_descPanel = new GridBagLayout();
		gbl_descPanel.columnWeights = new double[]{0.0, 0.0};
		gbl_descPanel.rowWeights = new double[]{0.0};
		descPanel.setLayout(gbl_descPanel);
		
		imagePanel = new JPanel();
		imagePanel.setBorder(new EmptyBorder(5, 0, 0, 0));
		imagePanel.setBackground(Color.LIGHT_GRAY);
		imagePanel.setPreferredSize(new Dimension(110, 90));
		GridBagConstraints gbc_imagePanel = new GridBagConstraints();
		gbc_imagePanel.weighty = 1.0;
		gbc_imagePanel.fill = GridBagConstraints.BOTH;
		gbc_imagePanel.insets = new Insets(0, 0, 0, 5);
		gbc_imagePanel.gridx = 0;
		gbc_imagePanel.gridy = 0;
		descPanel.add(imagePanel, gbc_imagePanel);
		
		bookImage = new JLabel("");
		bookImage.setIcon(new ImageIcon("Graphics\\example.jpg"));
		imagePanel.add(bookImage);
		
		bookDescriptionPanel = new JPanel();
		bookDescriptionPanel.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_bookDescriptionPanel = new GridBagConstraints();
		gbc_bookDescriptionPanel.weightx = 1.0;
		gbc_bookDescriptionPanel.fill = GridBagConstraints.BOTH;
		gbc_bookDescriptionPanel.gridx = 1;
		gbc_bookDescriptionPanel.gridy = 0;
		descPanel.add(bookDescriptionPanel, gbc_bookDescriptionPanel);
		bookDescriptionPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		authorPanel = new JPanel();
		authorPanel.setBackground(Color.LIGHT_GRAY);
		bookDescriptionPanel.add(authorPanel);
		GridBagLayout gbl_authorPanel = new GridBagLayout();
		gbl_authorPanel.columnWeights = new double[]{0.0, 0.0};
		gbl_authorPanel.rowWeights = new double[]{0.0};
		authorPanel.setLayout(gbl_authorPanel);
		
		authorLabel = new JLabel("Author(s):");
		authorLabel.setFont(new Font("8-bit Operator+", Font.PLAIN, 10));
		GridBagConstraints gbc_authorLabel = new GridBagConstraints();
		gbc_authorLabel.fill = GridBagConstraints.BOTH;
		gbc_authorLabel.insets = new Insets(5, 5, 5, 5);
		gbc_authorLabel.gridx = 0;
		gbc_authorLabel.gridy = 0;
		authorPanel.add(authorLabel, gbc_authorLabel);
		
		authorText = new JTextField();
		authorText.setEditable(false);
		authorText.setFont(new Font("8-bit Operator+", Font.BOLD, 9));
		authorText.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK));
		authorText.setPreferredSize(new Dimension(7, 0));
		GridBagConstraints gbc_authorText = new GridBagConstraints();
		gbc_authorText.insets = new Insets(0, 0, 0, 5);
		gbc_authorText.weightx = 1.0;
		gbc_authorText.fill = GridBagConstraints.BOTH;
		gbc_authorText.gridx = 1;
		gbc_authorText.gridy = 0;
		authorPanel.add(authorText, gbc_authorText);
		authorText.setColumns(10);
		
		datePanel = new JPanel();
		datePanel.setBackground(Color.LIGHT_GRAY);
		bookDescriptionPanel.add(datePanel);
		GridBagLayout gbl_datePanel = new GridBagLayout();
		gbl_datePanel.columnWeights = new double[]{0.0, 0.0};
		gbl_datePanel.rowWeights = new double[]{0.0};
		datePanel.setLayout(gbl_datePanel);
		
		dateLabel = new JLabel("Year:");
		dateLabel.setFont(new Font("8-bit Operator+", Font.PLAIN, 10));
		GridBagConstraints gbc_dateLabel = new GridBagConstraints();
		gbc_dateLabel.fill = GridBagConstraints.BOTH;
		gbc_dateLabel.insets = new Insets(5, 5, 0, 5);
		gbc_dateLabel.gridx = 0;
		gbc_dateLabel.gridy = 0;
		datePanel.add(dateLabel, gbc_dateLabel);
		
		dateText = new JTextField();
		dateText.setPreferredSize(new Dimension(7, 0));
		dateText.setEditable(false);
		dateText.setColumns(10);
		dateText.setFont(new Font("8-bit Operator+", Font.BOLD, 9));
		dateText.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK));
		GridBagConstraints gbc_dateText = new GridBagConstraints();
		gbc_dateText.weightx = 1.0;
		gbc_dateText.insets = new Insets(0, 0, 0, 5);
		gbc_dateText.fill = GridBagConstraints.BOTH;
		gbc_dateText.gridx = 1;
		gbc_dateText.gridy = 0;
		datePanel.add(dateText, gbc_dateText);
		
		languagePanel = new JPanel();
		languagePanel.setBackground(Color.LIGHT_GRAY);
		bookDescriptionPanel.add(languagePanel);
		GridBagLayout gbl_languagePanel = new GridBagLayout();
		gbl_languagePanel.columnWeights = new double[]{0.0, 0.0};
		gbl_languagePanel.rowWeights = new double[]{0.0};
		languagePanel.setLayout(gbl_languagePanel);
		
		languageLabel = new JLabel("Language:");
		languageLabel.setFont(new Font("8-bit Operator+", Font.PLAIN, 10));
		GridBagConstraints gbc_languageLabel = new GridBagConstraints();
		gbc_languageLabel.fill = GridBagConstraints.BOTH;
		gbc_languageLabel.insets = new Insets(5, 5, 0, 5);
		gbc_languageLabel.gridx = 0;
		gbc_languageLabel.gridy = 0;
		languagePanel.add(languageLabel, gbc_languageLabel);
		
		languageText = new JTextField();
		languageText.setPreferredSize(new Dimension(7, 0));
		languageText.setEditable(false);
		languageText.setColumns(10);
		languageText.setFont(new Font("8-bit Operator+", Font.BOLD, 9));
		languageText.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK));
		GridBagConstraints gbc_languageText = new GridBagConstraints();
		gbc_languageText.weightx = 1.0;
		gbc_languageText.insets = new Insets(0, 0, 0, 5);
		gbc_languageText.fill = GridBagConstraints.BOTH;
		gbc_languageText.gridx = 1;
		gbc_languageText.gridy = 0;
		languagePanel.add(languageText, gbc_languageText);
		
		idPanel = new JPanel();
		idPanel.setBackground(Color.LIGHT_GRAY);
		bookDescriptionPanel.add(idPanel);
		GridBagLayout gbl_idPanel = new GridBagLayout();
		gbl_idPanel.columnWeights = new double[]{0.0, 0.0};
		gbl_idPanel.rowWeights = new double[]{0.0};
		idPanel.setLayout(gbl_idPanel);
		
		idLabel = new JLabel("ID:");
		idLabel.setFont(new Font("8-bit Operator+", Font.PLAIN, 10));
		GridBagConstraints gbc_idLabel = new GridBagConstraints();
		gbc_idLabel.fill = GridBagConstraints.BOTH;
		gbc_idLabel.insets = new Insets(5, 5, 0, 5);
		gbc_idLabel.gridx = 0;
		gbc_idLabel.gridy = 0;
		idPanel.add(idLabel, gbc_idLabel);
		
		idText = new JTextField();
		idText.setPreferredSize(new Dimension(7, 0));
		idText.setEditable(false);
		idText.setColumns(10);
		idText.setFont(new Font("8-bit Operator+", Font.BOLD, 9));
		idText.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK));
		GridBagConstraints gbc_idText = new GridBagConstraints();
		gbc_idText.weightx = 1.0;
		gbc_idText.insets = new Insets(0, 0, 0, 5);
		gbc_idText.fill = GridBagConstraints.BOTH;
		gbc_idText.gridx = 1;
		gbc_idText.gridy = 0;
		idPanel.add(idText, gbc_idText);
		
		isbnPanel = new JPanel();
		isbnPanel.setBackground(Color.LIGHT_GRAY);
		bookDescriptionPanel.add(isbnPanel);
		GridBagLayout gbl_isbnPanel = new GridBagLayout();
		gbl_isbnPanel.columnWeights = new double[]{0.0, 0.0};
		gbl_isbnPanel.rowWeights = new double[]{0.0};
		isbnPanel.setLayout(gbl_isbnPanel);
		
		lblIsbn = new JLabel("ISBN:");
		lblIsbn.setFont(new Font("8-bit Operator+", Font.PLAIN, 10));
		GridBagConstraints gbc_lblIsbn = new GridBagConstraints();
		gbc_lblIsbn.fill = GridBagConstraints.BOTH;
		gbc_lblIsbn.insets = new Insets(5, 5, 0, 5);
		gbc_lblIsbn.gridx = 0;
		gbc_lblIsbn.gridy = 0;
		isbnPanel.add(lblIsbn, gbc_lblIsbn);
		
		isbnText = new JTextField();
		isbnText.setPreferredSize(new Dimension(7, 0));
		isbnText.setEditable(false);
		isbnText.setColumns(10);
		isbnText.setFont(new Font("8-bit Operator+", Font.BOLD, 9));
		isbnText.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK));
		GridBagConstraints gbc_isbnText = new GridBagConstraints();
		gbc_isbnText.weightx = 1.0;
		gbc_isbnText.insets = new Insets(0, 0, 0, 5);
		gbc_isbnText.fill = GridBagConstraints.BOTH;
		gbc_isbnText.gridx = 1;
		gbc_isbnText.gridy = 0;
		isbnPanel.add(isbnText, gbc_isbnText);
		
		ratingPanel = new JPanel();
		ratingPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		ratingPanel.setFont(new Font("8-bit Operator+", Font.BOLD, 10));
		ratingPanel.setBorder(new TitledBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(0, 0, 5, 0))), "Rating", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		ratingPanel.setBackground(Color.LIGHT_GRAY);
		ratingPanel.setForeground(Color.BLACK);
		ratingPanel.setPreferredSize(new Dimension(10, 70));
		GridBagConstraints gbc_ratingPanel = new GridBagConstraints();
		gbc_ratingPanel.weightx = 1.0;
		gbc_ratingPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_ratingPanel.insets = new Insets(0, 0, 5, 0);
		gbc_ratingPanel.gridx = 0;
		gbc_ratingPanel.gridy = 2;
		infoPanel.add(ratingPanel, gbc_ratingPanel);
		
		
		star1 = new JLabel("");
		star1.setAlignmentY(Component.TOP_ALIGNMENT);
		star1.setPreferredSize(new Dimension(32, 32));
		star1.setIcon(starIcon);
		ratingPanel.add(star1);
		
		star2 = new JLabel("");
		star2.setPreferredSize(new Dimension(32, 32));
		star2.setIcon(starIcon);
		star2.setAlignmentY(0.0f);
		ratingPanel.add(star2);
		
		star3 = new JLabel("");
		star3.setPreferredSize(new Dimension(32, 32));
		star3.setIcon(starIcon);
		star3.setAlignmentY(0.0f);
		ratingPanel.add(star3);
		
		star4 = new JLabel("");
		star4.setPreferredSize(new Dimension(32, 32));
		star4.setIcon(starIcon);
		star4.setAlignmentY(0.0f);
		ratingPanel.add(star4);
		
		star5 = new JLabel("");
		star5.setPreferredSize(new Dimension(32, 32));
		star5.setIcon(starIcon);
		star5.setAlignmentY(0.0f);
		ratingPanel.add(star5);
		
		buttonsPanel = new JPanel();
		buttonsPanel.setBorder(new CompoundBorder(new EmptyBorder(0, 5, 5, 5), new LineBorder(new Color(0, 0, 0))));
		buttonsPanel.setBackground(Color.LIGHT_GRAY);
		buttonsPanel.setPreferredSize(new Dimension(10, 50));
		buttonsPanel.setMaximumSize(new Dimension(32767, 50));
		GridBagConstraints gbc_buttonsPanel = new GridBagConstraints();
		gbc_buttonsPanel.insets = new Insets(0, 0, 5, 0);
		gbc_buttonsPanel.weightx = 1.0;
		gbc_buttonsPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonsPanel.gridx = 0;
		gbc_buttonsPanel.gridy = 3;
		infoPanel.add(buttonsPanel, gbc_buttonsPanel);
		
		editButton = new JButton("Edit");
		editButton.setBackground(Color.LIGHT_GRAY);
		editButton.setMinimumSize(new Dimension(32, 32));
		editButton.setHorizontalAlignment(SwingConstants.LEFT);
		editButton.setPreferredSize(new Dimension(90, 35));
		editButton.setFont(new Font("8-bit Operator+", Font.BOLD, 10));
		editButton.setIcon(editIcon);
		editButton.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK));
		buttonsPanel.add(editButton);
		editButton.addActionListener(this);
		
		deleteButton = new JButton("Delete");
		deleteButton.setActionCommand("Delete");
		deleteButton.setPreferredSize(new Dimension(90, 35));
		deleteButton.setMinimumSize(new Dimension(32, 32));
		deleteButton.setIcon(recycleIcon);
		deleteButton.setHorizontalAlignment(SwingConstants.LEFT);
		deleteButton.setFont(new Font("8-bit Operator+", Font.BOLD, 10));
		deleteButton.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK));
		deleteButton.setBackground(Color.LIGHT_GRAY);
		buttonsPanel.add(deleteButton);
		deleteButton.addActionListener(this);
		
		updateDescription();
		
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == deleteButton) { //delete button pressed
			
				deleteButtonPress();
			
			}
			else if (e.getSource() == editButton) { //edit button pressed
				editButtonPress();
			}
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		}
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource() == list && triggerUpdate == true) { //list item selected
			try {
				updateDescription();
			} catch (IOException e1) { 
				System.out.println(e1.getMessage());
			} 
		}
	}
}
