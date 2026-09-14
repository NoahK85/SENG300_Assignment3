import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.border.BevelBorder;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SpringLayout;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import java.awt.Label;
import java.awt.Rectangle;

public class libraryFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField searchText;
	private JTextField authorText;
	private JTextField dateText;
	private JTextField languageText;
	private JTextField idText;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					libraryFrame frame = new libraryFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public libraryFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("Graphics\\digitalLibrary.png"));
		setTitle("Desktop Library");
		setFont(new Font("8-bit Operator+ 8", Font.BOLD, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 555, 431);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(206, 0, 0));
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, new Color(255, 255, 255), new Color(128, 128, 128), Color.BLACK));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(new EmptyBorder(0, 10, 0, 0));
		titlePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		titlePanel.setBackground(Color.BLUE);
		titlePanel.setPreferredSize(new Dimension(10, 40));
		contentPane.add(titlePanel, BorderLayout.NORTH);
		
		JLabel titleLabel = new JLabel("Desktop Library");
		titleLabel.setMinimumSize(new Dimension(72, 5));
		titleLabel.setPreferredSize(new Dimension(72, 6));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		titleLabel.setIconTextGap(5);
		titleLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBackground(Color.WHITE);
		titleLabel.setFont(new Font("8-bit Operator+", Font.BOLD, 30));
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Graphics\\digitalLibrary.png"));
		titlePanel.add(lblNewLabel);
		titlePanel.add(titleLabel);
		
		JPanel lowerPanel = new JPanel();
		lowerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		lowerPanel.setBackground(Color.LIGHT_GRAY);
		contentPane.add(lowerPanel, BorderLayout.CENTER);
		lowerPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel libraryPanel = new JPanel();
		libraryPanel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.BLACK, Color.BLACK, Color.DARK_GRAY));
		libraryPanel.setBackground(Color.LIGHT_GRAY);
		lowerPanel.add(libraryPanel);
		GridBagLayout gbl_libraryPanel = new GridBagLayout();
		gbl_libraryPanel.columnWeights = new double[]{1.0};
		gbl_libraryPanel.rowWeights = new double[]{0.0, 0.0, 1.0};
		libraryPanel.setLayout(gbl_libraryPanel);
		
		JPanel searchPanel = new JPanel();
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
		
		JLabel searchLabel = new JLabel("Search:");
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
		
		JPanel sortPanel = new JPanel();
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
		
		JLabel sortLabel = new JLabel("Sort:");
		sortLabel.setIcon(new ImageIcon("Graphics\\filter.png"));
		sortLabel.setFont(new Font("8-bit Operator+", Font.BOLD, 13));
		sortPanel.add(sortLabel);
		
		JComboBox sortComboBox = new JComboBox();
		sortComboBox.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK));
		sortComboBox.setModel(new DefaultComboBoxModel(new String[] {"Book ID", "ISBN", "Authors", "Publication Date"}));
		sortComboBox.setPreferredSize(new Dimension(100, 21));
		sortComboBox.setFont(new Font("8-bit Operator+", Font.PLAIN, 10));
		sortPanel.add(sortComboBox);
		
		JPanel listPanel = new JPanel();
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK));
		listPanel.add(scrollPane);
		
		JList list = new JList();
		list.setFont(new Font("8-bit Operator+", Font.BOLD, 12));
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(list);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(Color.LIGHT_GRAY);
		infoPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		lowerPanel.add(infoPanel);
		GridBagLayout gbl_infoPanel = new GridBagLayout();
		gbl_infoPanel.columnWidths = new int[]{209, 0};
		gbl_infoPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_infoPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		infoPanel.setLayout(gbl_infoPanel);
		
		JPanel infoTitlePanel = new JPanel();
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
		
		JLabel bookTitlePanel = new JLabel("(Book Title)");
		bookTitlePanel.setHorizontalAlignment(SwingConstants.LEFT);
		bookTitlePanel.setHorizontalTextPosition(SwingConstants.LEFT);
		bookTitlePanel.setFont(new Font("8-bit Operator+", Font.BOLD, 20));
		infoTitlePanel.add(bookTitlePanel);
		
		JPanel descPanel = new JPanel();
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
		
		JPanel imagePanel = new JPanel();
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
		
		JLabel bookImage = new JLabel("");
		bookImage.setIcon(new ImageIcon("Graphics\\example.jpg"));
		imagePanel.add(bookImage);
		
		JPanel bookDescriptionPanel = new JPanel();
		bookDescriptionPanel.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_bookDescriptionPanel = new GridBagConstraints();
		gbc_bookDescriptionPanel.weightx = 1.0;
		gbc_bookDescriptionPanel.fill = GridBagConstraints.BOTH;
		gbc_bookDescriptionPanel.gridx = 1;
		gbc_bookDescriptionPanel.gridy = 0;
		descPanel.add(bookDescriptionPanel, gbc_bookDescriptionPanel);
		bookDescriptionPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel authorPanel = new JPanel();
		authorPanel.setBackground(Color.LIGHT_GRAY);
		bookDescriptionPanel.add(authorPanel);
		GridBagLayout gbl_authorPanel = new GridBagLayout();
		gbl_authorPanel.columnWeights = new double[]{0.0, 0.0};
		gbl_authorPanel.rowWeights = new double[]{0.0};
		authorPanel.setLayout(gbl_authorPanel);
		
		JLabel authorLabel = new JLabel("Author(s):");
		authorLabel.setFont(new Font("8-bit Operator+", Font.PLAIN, 10));
		GridBagConstraints gbc_authorLabel = new GridBagConstraints();
		gbc_authorLabel.fill = GridBagConstraints.BOTH;
		gbc_authorLabel.insets = new Insets(5, 5, 5, 5);
		gbc_authorLabel.gridx = 0;
		gbc_authorLabel.gridy = 0;
		authorPanel.add(authorLabel, gbc_authorLabel);
		
		authorText = new JTextField();
		authorText.setEditable(false);
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
		
		JPanel datePanel = new JPanel();
		datePanel.setBackground(Color.LIGHT_GRAY);
		bookDescriptionPanel.add(datePanel);
		GridBagLayout gbl_datePanel = new GridBagLayout();
		gbl_datePanel.columnWeights = new double[]{0.0, 0.0};
		gbl_datePanel.rowWeights = new double[]{0.0};
		datePanel.setLayout(gbl_datePanel);
		
		JLabel dateLabel = new JLabel("Year:");
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
		dateText.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK));
		GridBagConstraints gbc_dateText = new GridBagConstraints();
		gbc_dateText.weightx = 1.0;
		gbc_dateText.insets = new Insets(0, 0, 0, 5);
		gbc_dateText.fill = GridBagConstraints.BOTH;
		gbc_dateText.gridx = 1;
		gbc_dateText.gridy = 0;
		datePanel.add(dateText, gbc_dateText);
		
		JPanel languagePanel = new JPanel();
		languagePanel.setBackground(Color.LIGHT_GRAY);
		bookDescriptionPanel.add(languagePanel);
		GridBagLayout gbl_languagePanel = new GridBagLayout();
		gbl_languagePanel.columnWeights = new double[]{0.0, 0.0};
		gbl_languagePanel.rowWeights = new double[]{0.0};
		languagePanel.setLayout(gbl_languagePanel);
		
		JLabel languageLabel = new JLabel("Language:");
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
		languageText.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK));
		GridBagConstraints gbc_languageText = new GridBagConstraints();
		gbc_languageText.weightx = 1.0;
		gbc_languageText.insets = new Insets(0, 0, 0, 5);
		gbc_languageText.fill = GridBagConstraints.BOTH;
		gbc_languageText.gridx = 1;
		gbc_languageText.gridy = 0;
		languagePanel.add(languageText, gbc_languageText);
		
		JPanel idPanel = new JPanel();
		idPanel.setBackground(Color.LIGHT_GRAY);
		bookDescriptionPanel.add(idPanel);
		GridBagLayout gbl_idPanel = new GridBagLayout();
		gbl_idPanel.columnWeights = new double[]{0.0, 0.0};
		gbl_idPanel.rowWeights = new double[]{0.0};
		idPanel.setLayout(gbl_idPanel);
		
		JLabel idLabel = new JLabel("ID:");
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
		idText.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK));
		GridBagConstraints gbc_idText = new GridBagConstraints();
		gbc_idText.weightx = 1.0;
		gbc_idText.insets = new Insets(0, 0, 0, 5);
		gbc_idText.fill = GridBagConstraints.BOTH;
		gbc_idText.gridx = 1;
		gbc_idText.gridy = 0;
		idPanel.add(idText, gbc_idText);
		
		JPanel isbnPanel = new JPanel();
		isbnPanel.setBackground(Color.LIGHT_GRAY);
		bookDescriptionPanel.add(isbnPanel);
		GridBagLayout gbl_isbnPanel = new GridBagLayout();
		gbl_isbnPanel.columnWeights = new double[]{0.0, 0.0};
		gbl_isbnPanel.rowWeights = new double[]{0.0};
		isbnPanel.setLayout(gbl_isbnPanel);
		
		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setFont(new Font("8-bit Operator+", Font.PLAIN, 10));
		GridBagConstraints gbc_lblIsbn = new GridBagConstraints();
		gbc_lblIsbn.fill = GridBagConstraints.BOTH;
		gbc_lblIsbn.insets = new Insets(5, 5, 0, 5);
		gbc_lblIsbn.gridx = 0;
		gbc_lblIsbn.gridy = 0;
		isbnPanel.add(lblIsbn, gbc_lblIsbn);
		
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(7, 0));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.weightx = 1.0;
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		isbnPanel.add(textField, gbc_textField);
		
		JPanel ratingPanel = new JPanel();
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
		
		ImageIcon starIcon = new ImageIcon("Graphics\\star.png");
		JLabel star1 = new JLabel("");
		star1.setAlignmentY(Component.TOP_ALIGNMENT);
		star1.setPreferredSize(new Dimension(32, 32));
		star1.setIcon(starIcon);
		ratingPanel.add(star1);
		
		JLabel star2 = new JLabel("");
		star2.setPreferredSize(new Dimension(32, 32));
		star2.setIcon(starIcon);
		star2.setAlignmentY(0.0f);
		ratingPanel.add(star2);
		
		JLabel star3 = new JLabel("");
		star3.setPreferredSize(new Dimension(32, 32));
		star3.setIcon(starIcon);
		star3.setAlignmentY(0.0f);
		ratingPanel.add(star3);
		
		JLabel star4 = new JLabel("");
		star4.setPreferredSize(new Dimension(32, 32));
		star4.setIcon(starIcon);
		star4.setAlignmentY(0.0f);
		ratingPanel.add(star4);
		
		JLabel star5 = new JLabel("");
		star5.setPreferredSize(new Dimension(32, 32));
		star5.setIcon(starIcon);
		star5.setAlignmentY(0.0f);
		ratingPanel.add(star5);
		
		JPanel buttonsPanel = new JPanel();
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
		
		JButton editButton = new JButton("Edit");
		editButton.setBackground(Color.LIGHT_GRAY);
		editButton.setMinimumSize(new Dimension(32, 32));
		editButton.setHorizontalAlignment(SwingConstants.LEFT);
		editButton.setPreferredSize(new Dimension(90, 35));
		editButton.setFont(new Font("8-bit Operator+", Font.BOLD, 10));
		editButton.setIcon(new ImageIcon("Graphics\\edit.png"));
		editButton.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK));
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonsPanel.add(editButton);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.setActionCommand("Delete");
		deleteButton.setPreferredSize(new Dimension(90, 35));
		deleteButton.setMinimumSize(new Dimension(32, 32));
		deleteButton.setIcon(new ImageIcon("Graphics\\recycle.png"));
		deleteButton.setHorizontalAlignment(SwingConstants.LEFT);
		deleteButton.setFont(new Font("8-bit Operator+", Font.BOLD, 10));
		deleteButton.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK));
		deleteButton.setBackground(Color.LIGHT_GRAY);
		buttonsPanel.add(deleteButton);
		
		

	}
}
