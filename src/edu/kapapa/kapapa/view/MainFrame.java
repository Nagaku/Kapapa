/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kapapa.kapapa.view;

import com.toedter.calendar.JDateChooser;
import edu.kapapa.kapapa.controller.MemoController;
import edu.kapapa.kapapa.controller.UserController;
import edu.kapapa.kapapa.database.KapapaDatabase;
import edu.kapapa.kapapa.entity.Memo;
import edu.kapapa.kapapa.error.MemoException;
import edu.kapapa.kapapa.event.MemoListener;
import edu.kapapa.kapapa.model.MemoModel;
import edu.kapapa.kapapa.model.TabelMemoModel;
import edu.kapapa.kapapa.model.SokModel;
import edu.kapapa.kapapa.service.MemoDao;
import edu.kapapa.kapapa.service.UserDao;
import edu.kapapa.kapapa.entity.User;
import edu.kapapa.kapapa.error.UserException;
import edu.kapapa.kapapa.event.UserListener;
import edu.kapapa.kapapa.model.TabelUserModel;
import edu.kapapa.kapapa.model.UserModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author ASUS
 */

import javax.swing.table.DefaultTableModel;
public class MainFrame extends javax.swing.JFrame implements MemoListener, ListSelectionListener, UserListener{
	
	//inisialisasi objek objek
	private TabelMemoModel tabelModel;
	private TabelUserModel tabelModelUser;
	private MemoModel model;
	private UserModel modelUser;
	private MemoController controller;
	private UserController controllerUser;
	private User user;
	private LoginFrame login;
	private Image image;
	private ImageIcon img;
	private SokModel modelSok;
	private boolean notLoadedSok;
	ImageIcon[] fotoList;
	
	
	
	
	public MainFrame() {
		//LoginFrame loginFrame = new LoginFrame();
		//loginFrame.setVisible(true);
		setResizable(false);
		tabelModel = new TabelMemoModel();
		tabelModelUser = new TabelUserModel();
		
		//set listener model
		model = new MemoModel();
		model.setListener(this);
		modelUser = new UserModel();
		modelUser.setListener(this);
		modelSok = new SokModel();
		controller = new MemoController();
		controller.setModel(model);
		controllerUser = new UserController();
		controllerUser.setModel(modelUser);
		notLoadedSok = true;
		fotoList = new ImageIcon[5];
		initComponents();
		setIcon();
		//gradient
		tableMemoView.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
		tableMemoView.getTableHeader().setBackground(new Color(32,136,203));
		tableMemoView.getTableHeader().setForeground(new Color(255,255,255));
		
		userTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
		userTable.getTableHeader().setBackground(new Color(32,136,203));
		userTable.getTableHeader().setForeground(new Color(255,255,255));
		
		//set listener tabel
		tableMemoView.getSelectionModel().addListSelectionListener(this);
		tableMemoView.setModel(tabelModel);
		userTable.getSelectionModel().addListSelectionListener(this);
		userTable.setModel(tabelModelUser);
		
		jLabel1.setVisible(false);
		jLabel13.setVisible(false);
		txtId.setEditable(false);
		
		
		
	} 

	private void setIcon() {
		
		 ImageIcon img = new ImageIcon ("src/edu.kapapa.kapapa.image/kk.png");
	
	}

	//gradient
	class jPanelGradient1 extends JPanel {
		protected void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			int width = getWidth();
			int height = getHeight();
			
			Color color1 = new Color(45,100,240);
			Color color2 = new Color(52,204,236);
			GradientPaint gp = new GradientPaint(52,204, color1, 236,height, color2);
			g2d.setPaint(gp);
			g2d.fillRect(0, 0, width, height);
		}
	}
	//getter setter
	public JLabel getLblImage() {
		return lblImage;
	}

	public void setLblImage(JLabel lblImage) {
		this.lblImage = lblImage;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return this.user;
	}

	public JTextField getTxtId() {
		return txtId;
	}

	public JTextField getTxtMapel() {
		return txtMapel;
	}

	public JTextArea getTxtTugas() {
		return txtTugas;
	}

	public JDateChooser getTanggalChooser() {
		return tanggalChooser;
	}

	public void setTanggalChooser(JDateChooser tanggalChooser) {
		this.tanggalChooser = tanggalChooser;
	}

	public JTable getTableMemoView() {
		return tableMemoView;
	}

	public TabelMemoModel getTabelModel() {
		return tabelModel;
	}

	public JTextField getNamaF() {
		return namaF;
	}

	public JTextField getNisnF() {
		return nisnF;
	}

	public JTextField getNoabsenF() {
		return noabsenF;
	}

	public JTextField getNotelpF() {
		return notelpF;
	}

	public JTextField getPasswordF() {
		return passwordF;
	}

	public JTable getUserTable() {
		return userTable;
	}

	public JTextField getUsernameF() {
		return usernameF;
	}
	
	public JTextField getJabatanF() {
		return jabatanF;
	}

	public void setjLabel15(String jLabel14) {
		jLabel15.setText("Welcome, " + jLabel14);
	}

	public JLabel getLabelBendahara() {
		return labelBendahara;
	}

	public void setLabelBendahara(ImageIcon labelBendahara) {
		this.labelBendahara.setIcon(labelBendahara);
	}

	public JLabel getLabelKetuaKelas() {
		return labelKetuaKelas;
	}

	public void setLabelKetuaKelas(ImageIcon labelKetuaKelas) {
		this.labelKetuaKelas.setIcon(labelKetuaKelas);
	}

	public JLabel getLabelSekretasi() {
		return labelSekretasi;
	}

	public void setLabelSekretasi(ImageIcon labelSekretasi) {
		this.labelSekretasi.setIcon(labelSekretasi);
	}

	public JLabel getLabelWakilKetua() {
		return labelWakilKetua;
	}

	public void setLabelWakilKetua(ImageIcon labelWakilKetua) {
		this.labelWakilKetua.setIcon(labelWakilKetua);
	}

	public JLabel getLabelWaliKelas() {
		return labelWaliKelas;
	}

	public void setLabelWaliKelas(ImageIcon labelWaliKelas) {
		this.labelWaliKelas.setIcon(labelWaliKelas);
	}

	public void setTesss(String tesss) {
		this.namaKetuaKelas.setText(tesss);
	}
	
	

	/**
	 * This method is called from within the constructor to initialize the
	 * form. WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jLabel6 = new javax.swing.JLabel();
                AllPanel = new javax.swing.JPanel();
                MenuPanel = new jPanelGradient1();
                HomeButton = new javax.swing.JButton();
                TugasButton = new javax.swing.JButton();
                SokButton = new javax.swing.JButton();
                LogoutButton = new javax.swing.JButton();
                ProfileButton = new javax.swing.JButton();
                jSeparator1 = new javax.swing.JSeparator();
                jSeparator2 = new javax.swing.JSeparator();
                jSeparator3 = new javax.swing.JSeparator();
                jSeparator4 = new javax.swing.JSeparator();
                jSeparator5 = new javax.swing.JSeparator();
                jSeparator7 = new javax.swing.JSeparator();
                AboutButton = new javax.swing.JButton();
                MainPanel = new javax.swing.JPanel();
                HomePanel = new javax.swing.JPanel();
                jLabel15 = new javax.swing.JLabel();
                jLabel18 = new javax.swing.JLabel();
                TugasPanel = new javax.swing.JPanel();
                jScrollPane1 = new javax.swing.JScrollPane();
                tableMemoView = new javax.swing.JTable();
                jPanel1 = new javax.swing.JPanel();
                btnUpdate = new javax.swing.JButton();
                btnView = new javax.swing.JButton();
                btnAdd = new javax.swing.JButton();
                btnDelete = new javax.swing.JButton();
                jLabel16 = new javax.swing.JLabel();
                dataPanel = new javax.swing.JPanel();
                txtId = new javax.swing.JTextField();
                txtMapel = new javax.swing.JTextField();
                lblId = new javax.swing.JLabel();
                lblMapel = new javax.swing.JLabel();
                lblTugas = new javax.swing.JLabel();
                btnSave = new javax.swing.JButton();
                jLabel1 = new javax.swing.JLabel();
                tanggalChooser = new com.toedter.calendar.JDateChooser();
                lblTugas1 = new javax.swing.JLabel();
                jScrollPane5 = new javax.swing.JScrollPane();
                txtTugas = new javax.swing.JTextArea();
                AdminPanel = new javax.swing.JPanel();
                jScrollPane3 = new javax.swing.JScrollPane();
                userTable = new javax.swing.JTable();
                jLabel5 = new javax.swing.JLabel();
                jPanel3 = new javax.swing.JPanel();
                adminView = new javax.swing.JButton();
                adminAdd = new javax.swing.JButton();
                adminUpdate = new javax.swing.JButton();
                adminDelete = new javax.swing.JButton();
                UserPanel = new javax.swing.JPanel();
                jLabel2 = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                jLabel7 = new javax.swing.JLabel();
                nisnF = new javax.swing.JTextField();
                usernameF = new javax.swing.JTextField();
                passwordF = new javax.swing.JTextField();
                namaF = new javax.swing.JTextField();
                notelpF = new javax.swing.JTextField();
                noabsenF = new javax.swing.JTextField();
                jabatanF = new javax.swing.JTextField();
                jLabel8 = new javax.swing.JLabel();
                jLabel9 = new javax.swing.JLabel();
                jLabel10 = new javax.swing.JLabel();
                jLabel11 = new javax.swing.JLabel();
                jLabel12 = new javax.swing.JLabel();
                btnOk = new javax.swing.JButton();
                jLabel13 = new javax.swing.JLabel();
                lblImage = new javax.swing.JLabel();
                btnUpload = new javax.swing.JButton();
                jLabel19 = new javax.swing.JLabel();
                SokPanel = new javax.swing.JPanel();
                jLabel23 = new javax.swing.JLabel();
                jLabel24 = new javax.swing.JLabel();
                jLabel25 = new javax.swing.JLabel();
                jLabel26 = new javax.swing.JLabel();
                jLabel27 = new javax.swing.JLabel();
                labelWaliKelas = new javax.swing.JLabel();
                labelKetuaKelas = new javax.swing.JLabel();
                labelWakilKetua = new javax.swing.JLabel();
                labelSekretasi = new javax.swing.JLabel();
                labelBendahara = new javax.swing.JLabel();
                namaBendahara = new javax.swing.JLabel();
                namaSekretaris = new javax.swing.JLabel();
                namaWakilKetua = new javax.swing.JLabel();
                namaKetuaKelas = new javax.swing.JLabel();
                namaWaliKelas = new javax.swing.JLabel();
                jLabel21 = new javax.swing.JLabel();
                jLabel17 = new javax.swing.JLabel();
                AboutPanel = new javax.swing.JPanel();
                jLabel14 = new javax.swing.JLabel();
                jLabel28 = new javax.swing.JLabel();

                jLabel6.setText("jLabel6");

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                MenuPanel.setBackground(new java.awt.Color(0, 204, 255));

                HomeButton.setBackground(new Color(0,0,0,1));
                HomeButton.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                HomeButton.setForeground(new java.awt.Color(255, 255, 255));
                HomeButton.setIcon(new javax.swing.ImageIcon("D:\\Informatika\\Semester 3\\Pemrograman Berbasis Obyek\\Tugas Besar\\Desktop\\Kapapa\\Images\\iconfinder_Streamline-18_185038(1).png")); // NOI18N
                HomeButton.setText(" Home ");
                HomeButton.setBorder(null);
                HomeButton.setBorderPainted(false);
                HomeButton.setContentAreaFilled(false);
                HomeButton.setVerifyInputWhenFocusTarget(false);
                HomeButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                HomeButtonActionPerformed(evt);
                        }
                });

                TugasButton.setBackground(new Color(0,0,0,1));
                TugasButton.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                TugasButton.setForeground(new java.awt.Color(255, 255, 255));
                TugasButton.setIcon(new javax.swing.ImageIcon("D:\\Informatika\\Semester 3\\Pemrograman Berbasis Obyek\\Tugas Besar\\Desktop\\Kapapa\\Images\\iconfinder_book_322421.png")); // NOI18N
                TugasButton.setText(" Task");
                TugasButton.setToolTipText("");
                TugasButton.setBorder(null);
                TugasButton.setBorderPainted(false);
                TugasButton.setContentAreaFilled(false);
                TugasButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                TugasButtonActionPerformed(evt);
                        }
                });

                SokButton.setBackground(new Color(0,0,0,1));
                SokButton.setForeground(new java.awt.Color(255, 255, 255));
                SokButton.setIcon(new javax.swing.ImageIcon("D:\\Informatika\\Semester 3\\Pemrograman Berbasis Obyek\\Tugas Besar\\Desktop\\Kapapa\\Images\\link.png")); // NOI18N
                SokButton.setText(" Organization Structure");
                SokButton.setBorder(null);
                SokButton.setBorderPainted(false);
                SokButton.setContentAreaFilled(false);
                SokButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                SokButtonActionPerformed(evt);
                        }
                });

                LogoutButton.setBackground(new Color(0,0,0,1));
                LogoutButton.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                LogoutButton.setForeground(new java.awt.Color(255, 255, 255));
                LogoutButton.setIcon(new javax.swing.ImageIcon("D:\\Informatika\\Semester 3\\Pemrograman Berbasis Obyek\\Tugas Besar\\Desktop\\Kapapa\\Images\\logout1.png")); // NOI18N
                LogoutButton.setText(" Logout");
                LogoutButton.setToolTipText("");
                LogoutButton.setBorder(null);
                LogoutButton.setContentAreaFilled(false);
                LogoutButton.setDefaultCapable(false);
                LogoutButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                LogoutButtonActionPerformed(evt);
                        }
                });

                ProfileButton.setBackground(new Color(0,0,0,1));
                ProfileButton.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                ProfileButton.setForeground(new java.awt.Color(255, 255, 255));
                ProfileButton.setIcon(new javax.swing.ImageIcon("D:\\Informatika\\Semester 3\\Pemrograman Berbasis Obyek\\Tugas Besar\\Desktop\\Kapapa\\Images\\businessman.png")); // NOI18N
                ProfileButton.setText(" Profile");
                ProfileButton.setBorder(null);
                ProfileButton.setContentAreaFilled(false);
                ProfileButton.setDefaultCapable(false);
                ProfileButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                ProfileButtonActionPerformed(evt);
                        }
                });

                AboutButton.setBackground(new Color(0,0,0,1));
                AboutButton.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                AboutButton.setForeground(new java.awt.Color(255, 255, 255));
                AboutButton.setIcon(new javax.swing.ImageIcon("D:\\Informatika\\Semester 3\\Pemrograman Berbasis Obyek\\Tugas Besar\\Desktop\\Kapapa\\Images\\info.png")); // NOI18N
                AboutButton.setText(" About Us");
                AboutButton.setBorderPainted(false);
                AboutButton.setContentAreaFilled(false);
                AboutButton.setFocusCycleRoot(true);
                AboutButton.setHideActionText(true);
                AboutButton.setInheritsPopupMenu(true);
                AboutButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                AboutButtonActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout MenuPanelLayout = new javax.swing.GroupLayout(MenuPanel);
                MenuPanel.setLayout(MenuPanelLayout);
                MenuPanelLayout.setHorizontalGroup(
                        MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(MenuPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jSeparator5)
                                                .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(SokButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                                                .addComponent(ProfileButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(LogoutButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(TugasButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(HomeButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jSeparator7))
                                        .addComponent(AboutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(65, Short.MAX_VALUE))
                );
                MenuPanelLayout.setVerticalGroup(
                        MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuPanelLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(HomeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TugasButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(SokButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ProfileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(AboutButton, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                                .addGap(20, 20, 20)
                                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(LogoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38))
                );

                MainPanel.setBackground(new java.awt.Color(255, 255, 255));
                MainPanel.setLayout(new java.awt.CardLayout());

                HomePanel.setBackground(new java.awt.Color(255, 255, 255));
                HomePanel.setPreferredSize(new java.awt.Dimension(1020, 583));
                HomePanel.setLayout(null);

                jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                jLabel15.setForeground(new java.awt.Color(255, 255, 255));
                jLabel15.setText("jLabel15");
                HomePanel.add(jLabel15);
                jLabel15.setBounds(450, 440, 323, 151);

                jLabel18.setIcon(new javax.swing.ImageIcon("C:\\Users\\ASUS\\Pictures\\Screenshots\\Screenshot (4).png")); // NOI18N
                HomePanel.add(jLabel18);
                jLabel18.setBounds(0, 0, 1110, 660);

                MainPanel.add(HomePanel, "card4");

                TugasPanel.setBackground(new java.awt.Color(255, 255, 255));
                TugasPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                TugasPanel.setPreferredSize(new java.awt.Dimension(1020, 583));
                TugasPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
                jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                tableMemoView.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {
                                {null, null, null, null},
                                {null, null, null, null},
                                {null, null, null, null},
                                {null, null, null, null}
                        },
                        new String [] {
                                "Title 1", "Title 2", "Title 3", "Title 4"
                        }
                ));
                tableMemoView.setIntercellSpacing(new java.awt.Dimension(0, 0));
                tableMemoView.setRowHeight(25);
                tableMemoView.setSelectionBackground(new java.awt.Color(51, 204, 255));
                tableMemoView.setShowVerticalLines(false);
                jScrollPane1.setViewportView(tableMemoView);

                TugasPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 200, 870, 318));

                jPanel1.setBackground(new java.awt.Color(51, 204, 255));

                btnUpdate.setBackground(new java.awt.Color(51, 204, 255));
                btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
                btnUpdate.setIcon(new javax.swing.ImageIcon("D:\\Informatika\\Semester 3\\Pemrograman Berbasis Obyek\\Tugas Besar\\Desktop\\Kapapa\\Images\\refresh.png")); // NOI18N
                btnUpdate.setBorder(null);
                btnUpdate.setMaximumSize(new java.awt.Dimension(63, 25));
                btnUpdate.setMinimumSize(new java.awt.Dimension(63, 25));
                btnUpdate.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnUpdateActionPerformed(evt);
                        }
                });

                btnView.setBackground(new java.awt.Color(51, 204, 255));
                btnView.setForeground(new java.awt.Color(255, 255, 255));
                btnView.setIcon(new javax.swing.ImageIcon("D:\\Informatika\\Semester 3\\Pemrograman Berbasis Obyek\\Tugas Besar\\Desktop\\Kapapa\\Images\\view(1).png")); // NOI18N
                btnView.setBorder(null);
                btnView.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnViewActionPerformed(evt);
                        }
                });

                btnAdd.setBackground(new java.awt.Color(51, 204, 255));
                btnAdd.setForeground(new java.awt.Color(255, 255, 255));
                btnAdd.setIcon(new javax.swing.ImageIcon("D:\\Informatika\\Semester 3\\Pemrograman Berbasis Obyek\\Tugas Besar\\Desktop\\Kapapa\\Images\\plus.png")); // NOI18N
                btnAdd.setBorder(null);
                btnAdd.setMaximumSize(new java.awt.Dimension(63, 25));
                btnAdd.setMinimumSize(new java.awt.Dimension(63, 25));
                btnAdd.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAddActionPerformed(evt);
                        }
                });

                btnDelete.setBackground(new java.awt.Color(51, 204, 255));
                btnDelete.setForeground(new java.awt.Color(255, 255, 255));
                btnDelete.setIcon(new javax.swing.ImageIcon("D:\\Informatika\\Semester 3\\Pemrograman Berbasis Obyek\\Tugas Besar\\Desktop\\Kapapa\\Images\\bin.png")); // NOI18N
                btnDelete.setBorder(null);
                btnDelete.setMaximumSize(new java.awt.Dimension(63, 25));
                btnDelete.setMinimumSize(new java.awt.Dimension(63, 25));
                btnDelete.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnDeleteActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(btnView)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(786, Short.MAX_VALUE))
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(30, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25))
                );

                TugasPanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1020, -1));

                jLabel16.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
                jLabel16.setIcon(new javax.swing.ImageIcon("D:\\Informatika\\Semester 3\\Pemrograman Berbasis Obyek\\Tugas Besar\\Desktop\\Kapapa\\Images\\kk - Copy.png")); // NOI18N
                jLabel16.setText("Task");
                TugasPanel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, -1));

                MainPanel.add(TugasPanel, "card3");

                dataPanel.setBackground(new java.awt.Color(255, 255, 255));

                txtId.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtIdActionPerformed(evt);
                        }
                });

                txtMapel.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtMapelActionPerformed(evt);
                        }
                });

                lblId.setText("ID: ");

                lblMapel.setText("Mata Pelajaran:");

                lblTugas.setText("Tugas: ");

                btnSave.setText("SAVE");
                btnSave.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnSaveActionPerformed(evt);
                        }
                });

                lblTugas1.setText("Tanggal:");

                jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                txtTugas.setColumns(20);
                txtTugas.setRows(5);
                jScrollPane5.setViewportView(txtTugas);

                javax.swing.GroupLayout dataPanelLayout = new javax.swing.GroupLayout(dataPanel);
                dataPanel.setLayout(dataPanelLayout);
                dataPanelLayout.setHorizontalGroup(
                        dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(dataPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblMapel)
                                        .addComponent(lblTugas)
                                        .addComponent(lblTugas1)
                                        .addComponent(lblId))
                                .addGap(114, 114, 114)
                                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(dataPanelLayout.createSequentialGroup()
                                                .addComponent(btnSave)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(dataPanelLayout.createSequentialGroup()
                                                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane5)
                                                        .addGroup(dataPanelLayout.createSequentialGroup()
                                                                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(txtMapel, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(0, 60, Short.MAX_VALUE))
                                                        .addComponent(tanggalChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(341, 341, 341)
                                                .addComponent(jLabel1)
                                                .addGap(70, 70, 70))))
                );
                dataPanelLayout.setVerticalGroup(
                        dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(dataPanelLayout.createSequentialGroup()
                                .addGap(102, 102, 102)
                                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblId))
                                .addGap(30, 30, 30)
                                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtMapel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblMapel))
                                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(dataPanelLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel1)
                                                .addContainerGap(478, Short.MAX_VALUE))
                                        .addGroup(dataPanelLayout.createSequentialGroup()
                                                .addGap(64, 64, 64)
                                                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(dataPanelLayout.createSequentialGroup()
                                                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(tanggalChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(dataPanelLayout.createSequentialGroup()
                                                                .addComponent(lblTugas)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(lblTugas1)))
                                                .addGap(88, 88, 88)
                                                .addComponent(btnSave)
                                                .addGap(52, 52, 52))))
                );

                MainPanel.add(dataPanel, "card5");

                AdminPanel.setBackground(new java.awt.Color(255, 255, 255));

                jScrollPane3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
                jScrollPane3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                userTable.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {
                                {null, null, null, null},
                                {null, null, null, null},
                                {null, null, null, null},
                                {null, null, null, null}
                        },
                        new String [] {
                                "Title 1", "Title 2", "Title 3", "Title 4"
                        }
                ));
                userTable.setIntercellSpacing(new java.awt.Dimension(0, 0));
                userTable.setRowHeight(25);
                userTable.setSelectionBackground(new java.awt.Color(0, 204, 255));
                userTable.setShowVerticalLines(false);
                jScrollPane3.setViewportView(userTable);

                jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
                jLabel5.setIcon(new javax.swing.ImageIcon("D:\\Informatika\\Semester 3\\Pemrograman Berbasis Obyek\\Tugas Besar\\Desktop\\Kapapa\\Images\\kk - Copy.png")); // NOI18N
                jLabel5.setText("USER ");

                jPanel3.setBackground(new java.awt.Color(51, 204, 255));

                adminView.setBackground(new java.awt.Color(51, 204, 255));
                adminView.setIcon(new javax.swing.ImageIcon("D:\\Informatika\\Semester 3\\Pemrograman Berbasis Obyek\\Tugas Besar\\Desktop\\Kapapa\\Images\\view(1).png")); // NOI18N
                adminView.setBorder(null);
                adminView.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                adminViewActionPerformed(evt);
                        }
                });

                adminAdd.setBackground(new java.awt.Color(51, 204, 255));
                adminAdd.setIcon(new javax.swing.ImageIcon("D:\\Informatika\\Semester 3\\Pemrograman Berbasis Obyek\\Tugas Besar\\Desktop\\Kapapa\\Images\\plus.png")); // NOI18N
                adminAdd.setBorder(null);
                adminAdd.setMaximumSize(new java.awt.Dimension(63, 25));
                adminAdd.setMinimumSize(new java.awt.Dimension(63, 25));
                adminAdd.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                adminAddActionPerformed(evt);
                        }
                });

                adminUpdate.setBackground(new java.awt.Color(51, 204, 255));
                adminUpdate.setIcon(new javax.swing.ImageIcon("D:\\Informatika\\Semester 3\\Pemrograman Berbasis Obyek\\Tugas Besar\\Desktop\\Kapapa\\Images\\refresh.png")); // NOI18N
                adminUpdate.setBorder(null);
                adminUpdate.setMaximumSize(new java.awt.Dimension(63, 25));
                adminUpdate.setMinimumSize(new java.awt.Dimension(63, 25));
                adminUpdate.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                adminUpdateActionPerformed(evt);
                        }
                });

                adminDelete.setBackground(new java.awt.Color(51, 204, 255));
                adminDelete.setIcon(new javax.swing.ImageIcon("D:\\Informatika\\Semester 3\\Pemrograman Berbasis Obyek\\Tugas Besar\\Desktop\\Kapapa\\Images\\bin.png")); // NOI18N
                adminDelete.setBorder(null);
                adminDelete.setMaximumSize(new java.awt.Dimension(63, 25));
                adminDelete.setMinimumSize(new java.awt.Dimension(63, 25));
                adminDelete.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                adminDeleteActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addComponent(adminView)
                                .addGap(18, 18, 18)
                                .addComponent(adminAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(adminUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(adminDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel3Layout.setVerticalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap(30, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(adminDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(adminUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(adminAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(adminView, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25))
                );

                javax.swing.GroupLayout AdminPanelLayout = new javax.swing.GroupLayout(AdminPanel);
                AdminPanel.setLayout(AdminPanelLayout);
                AdminPanelLayout.setHorizontalGroup(
                        AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AdminPanelLayout.createSequentialGroup()
                                .addContainerGap(88, Short.MAX_VALUE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 868, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68))
                        .addGroup(AdminPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                AdminPanelLayout.setVerticalGroup(
                        AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(AdminPanelLayout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(167, Short.MAX_VALUE))
                );

                MainPanel.add(AdminPanel, "card7");

                UserPanel.setBackground(new java.awt.Color(255, 255, 255));

                jLabel2.setBackground(new java.awt.Color(255, 255, 255));
                jLabel2.setText("USER");

                jLabel3.setText("NISN");

                jLabel7.setText("USERNAME");

                noabsenF.setToolTipText("");

                jLabel8.setText("PASSWORD");

                jLabel9.setText("NAMA");

                jLabel10.setText("NO ABSEN");

                jLabel11.setText("JABATAN");

                jLabel12.setText("NO TELP");

                btnOk.setText("OK");
                btnOk.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnOkActionPerformed(evt);
                        }
                });

                jLabel13.setText("jLabel13");

                btnUpload.setText("UPLOAD PHOTO");
                btnUpload.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnUploadActionPerformed(evt);
                        }
                });

                jLabel19.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
                jLabel19.setIcon(new javax.swing.ImageIcon("D:\\Informatika\\Semester 3\\Pemrograman Berbasis Obyek\\Tugas Besar\\Desktop\\Kapapa\\Images\\kk - Copy.png")); // NOI18N
                jLabel19.setText("Profile");

                javax.swing.GroupLayout UserPanelLayout = new javax.swing.GroupLayout(UserPanel);
                UserPanel.setLayout(UserPanelLayout);
                UserPanelLayout.setHorizontalGroup(
                        UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UserPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel13))
                        .addGroup(UserPanelLayout.createSequentialGroup()
                                .addGap(118, 118, 118)
                                .addGroup(UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(UserPanelLayout.createSequentialGroup()
                                                                .addComponent(jLabel11)
                                                                .addGap(83, 83, 83)
                                                                .addComponent(jabatanF, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(UserPanelLayout.createSequentialGroup()
                                                                .addComponent(btnUpload)
                                                                .addGap(144, 144, 144)
                                                                .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(UserPanelLayout.createSequentialGroup()
                                                        .addComponent(jLabel12)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(notelpF, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(UserPanelLayout.createSequentialGroup()
                                                        .addComponent(jLabel10)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(noabsenF, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UserPanelLayout.createSequentialGroup()
                                                        .addComponent(jLabel9)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(namaF, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(UserPanelLayout.createSequentialGroup()
                                                        .addComponent(jLabel8)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(passwordF, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(UserPanelLayout.createSequentialGroup()
                                                        .addComponent(jLabel7)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(usernameF, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(UserPanelLayout.createSequentialGroup()
                                                        .addComponent(jLabel3)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(nisnF, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(88, 88, 88)
                                .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                                .addGap(98, 98, 98))
                        .addGroup(UserPanelLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel19)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                UserPanelLayout.setVerticalGroup(
                        UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(UserPanelLayout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(UserPanelLayout.createSequentialGroup()
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel3)
                                                        .addComponent(nisnF, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel7)
                                                        .addComponent(usernameF, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(passwordF, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel8))
                                                .addGap(18, 18, 18)
                                                .addGroup(UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(namaF, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel9))
                                                .addGap(18, 18, 18)
                                                .addGroup(UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel10)
                                                        .addComponent(noabsenF, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(25, 25, 25)
                                                .addGroup(UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel12)
                                                        .addComponent(notelpF, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                                .addGroup(UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel11)
                                        .addComponent(jabatanF, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(51, 51, 51)
                                .addGroup(UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(41, 41, 41)
                                .addComponent(jLabel13))
                );

                MainPanel.add(UserPanel, "card8");

                SokPanel.setBackground(new java.awt.Color(255, 255, 255));
                SokPanel.setPreferredSize(new java.awt.Dimension(1020, 535));
                SokPanel.setLayout(null);

                jLabel23.setText("Wali Kelas");
                SokPanel.add(jLabel23);
                jLabel23.setBounds(60, 370, 70, 16);

                jLabel24.setText("Ketua Kelas");
                SokPanel.add(jLabel24);
                jLabel24.setBounds(290, 370, 90, 16);

                jLabel25.setText("Wakil Ketua Kelas");
                SokPanel.add(jLabel25);
                jLabel25.setBounds(500, 370, 110, 16);

                jLabel26.setText("Sekretaris");
                SokPanel.add(jLabel26);
                jLabel26.setBounds(830, 180, 58, 16);

                jLabel27.setText("Bendahara");
                SokPanel.add(jLabel27);
                jLabel27.setBounds(830, 590, 61, 30);

                labelWaliKelas.setIcon(new javax.swing.ImageIcon("D:\\Informatika\\Semester 3\\Pemrograman Berbasis Obyek\\Tugas Besar\\fiqri.png")); // NOI18N
                SokPanel.add(labelWaliKelas);
                labelWaliKelas.setBounds(40, 250, 120, 110);

                labelKetuaKelas.setIcon(new javax.swing.ImageIcon("D:\\Informatika\\Semester 3\\Pemrograman Berbasis Obyek\\Tugas Besar\\fiqri.png")); // NOI18N
                SokPanel.add(labelKetuaKelas);
                labelKetuaKelas.setBounds(270, 250, 120, 110);

                labelWakilKetua.setIcon(new javax.swing.ImageIcon("D:\\Informatika\\Semester 3\\Pemrograman Berbasis Obyek\\Tugas Besar\\fiqri.png")); // NOI18N
                SokPanel.add(labelWakilKetua);
                labelWakilKetua.setBounds(500, 250, 110, 110);

                labelSekretasi.setIcon(new javax.swing.ImageIcon("D:\\Informatika\\Semester 3\\Pemrograman Berbasis Obyek\\Tugas Besar\\fiqri.png")); // NOI18N
                SokPanel.add(labelSekretasi);
                labelSekretasi.setBounds(810, 60, 100, 110);

                labelBendahara.setIcon(new javax.swing.ImageIcon("D:\\Informatika\\Semester 3\\Pemrograman Berbasis Obyek\\Tugas Besar\\fiqri.png")); // NOI18N
                labelBendahara.setText("jLabel22");
                SokPanel.add(labelBendahara);
                labelBendahara.setBounds(810, 450, 100, 110);

                namaBendahara.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                namaBendahara.setText("jLabel17");
                SokPanel.add(namaBendahara);
                namaBendahara.setBounds(840, 570, 48, 16);

                namaSekretaris.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                namaSekretaris.setText("jLabel17");
                SokPanel.add(namaSekretaris);
                namaSekretaris.setBounds(840, 210, 48, 16);

                namaWakilKetua.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                namaWakilKetua.setText("jLabel17");
                SokPanel.add(namaWakilKetua);
                namaWakilKetua.setBounds(530, 400, 48, 16);

                namaKetuaKelas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                namaKetuaKelas.setText("jLabel17");
                SokPanel.add(namaKetuaKelas);
                namaKetuaKelas.setBounds(300, 400, 48, 16);

                namaWaliKelas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                namaWaliKelas.setText("jLabel17");
                SokPanel.add(namaWaliKelas);
                namaWaliKelas.setBounds(70, 400, 48, 16);

                jLabel21.setIcon(new javax.swing.ImageIcon("D:\\Informatika\\Semester 3\\Pemrograman Berbasis Obyek\\Tugas Besar\\tree.png")); // NOI18N
                SokPanel.add(jLabel21);
                jLabel21.setBounds(30, 100, 860, 460);

                jLabel17.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
                jLabel17.setIcon(new javax.swing.ImageIcon("D:\\Informatika\\Semester 3\\Pemrograman Berbasis Obyek\\Tugas Besar\\Desktop\\Kapapa\\Images\\kk - Copy.png")); // NOI18N
                jLabel17.setText("Organization Structure");
                SokPanel.add(jLabel17);
                jLabel17.setBounds(40, 0, 230, 50);

                MainPanel.add(SokPanel, "card4");

                AboutPanel.setBackground(new java.awt.Color(255, 255, 255));

                jLabel14.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
                jLabel14.setIcon(new javax.swing.ImageIcon("D:\\Informatika\\Semester 3\\Pemrograman Berbasis Obyek\\Tugas Besar\\Desktop\\Kapapa\\Images\\kk - Copy.png")); // NOI18N
                jLabel14.setText("About Us");

                jLabel28.setIcon(new javax.swing.ImageIcon("D:\\Informatika\\Semester 3\\Pemrograman Berbasis Obyek\\Tugas Besar\\about.jpg")); // NOI18N

                javax.swing.GroupLayout AboutPanelLayout = new javax.swing.GroupLayout(AboutPanel);
                AboutPanel.setLayout(AboutPanelLayout);
                AboutPanelLayout.setHorizontalGroup(
                        AboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(AboutPanelLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(AboutPanelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel28))
                );
                AboutPanelLayout.setVerticalGroup(
                        AboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(AboutPanelLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                MainPanel.add(AboutPanel, "card8");

                javax.swing.GroupLayout AllPanelLayout = new javax.swing.GroupLayout(AllPanel);
                AllPanel.setLayout(AllPanelLayout);
                AllPanelLayout.setHorizontalGroup(
                        AllPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(AllPanelLayout.createSequentialGroup()
                                .addComponent(MenuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                );
                AllPanelLayout.setVerticalGroup(
                        AllPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(AllPanelLayout.createSequentialGroup()
                                .addComponent(MenuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(AllPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(AllPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                );

                pack();
                setLocationRelativeTo(null);
        }// </editor-fold>//GEN-END:initComponents
	//panel tugas
        private void TugasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TugasButtonActionPerformed
                // TODO add your handling code here:
		MainPanel.removeAll();
		MainPanel.repaint();
		MainPanel.revalidate();
		
		if (user.getJabatan().equals("Siswa") ){
			btnAdd.setVisible(false);
			btnUpdate.setVisible(false);
			btnDelete.setVisible(false);
			adminAdd.setVisible(false);
			adminUpdate.setVisible(false);
			adminDelete.setVisible(false);
		}
		
		MainPanel.add(TugasPanel);
		MainPanel.repaint();
		MainPanel.revalidate();
		
		
        }//GEN-LAST:event_TugasButtonActionPerformed
	//panel home
        private void HomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeButtonActionPerformed
                // TODO add your handling code here:
		MainPanel.removeAll();
		MainPanel.repaint();
		MainPanel.revalidate();
		
		MainPanel.add(HomePanel);
		MainPanel.repaint();
		MainPanel.revalidate();
        }//GEN-LAST:event_HomeButtonActionPerformed
	//tambah tugas
        private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
                // TODO add your handling code here:
		MainPanel.removeAll();
		MainPanel.repaint();
		MainPanel.revalidate();
		jLabel1.setText("add");
		MainPanel.add(dataPanel);
		MainPanel.repaint();
		MainPanel.revalidate();
		txtMapel.setEditable(true);
		txtTugas.setEditable(true);
		btnSave.setVisible(true);
		txtMapel.setText("");
		txtTugas.setText("");
        }//GEN-LAST:event_btnAddActionPerformed
	//update tugas
        private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
                // TODO add your handling code here:
		MainPanel.removeAll();
		MainPanel.repaint();
		MainPanel.revalidate();
		jLabel1.setText("update");
		MainPanel.add(dataPanel);
		MainPanel.repaint();
		MainPanel.revalidate();
		try {
			Memo model = tabelModel.get(tableMemoView.getSelectedRow());
			txtId.setText(model.getIdMemo() + "");
			txtMapel.setText(model.getMataPelajaran());
			txtTugas.setText(model.getTugas());
		} catch (IndexOutOfBoundsException exception) {
		}
		txtMapel.setEditable(true);
		txtTugas.setEditable(true);
		btnSave.setVisible(true);
        }//GEN-LAST:event_btnUpdateActionPerformed
	//hapus tugas
        private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
                // TODO add your handling code here:
		controller.deleteMemo(this);
        }//GEN-LAST:event_btnDeleteActionPerformed
	//tombol simpann
        private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
                // TODO add your handling code here:
                if (jLabel1.getText().equals("add"))
                controller.insertMemo(this);
                else
                controller.updateMemo(this);
		
		
		
        }//GEN-LAST:event_btnSaveActionPerformed

        private void txtMapelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMapelActionPerformed
                // TODO add your handling code here:
        }//GEN-LAST:event_txtMapelActionPerformed

        private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
                // TODO add your handling code here:
        }//GEN-LAST:event_txtIdActionPerformed
	//view tugas
        private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
                // TODO add your handling code here:
		MainPanel.removeAll();
		MainPanel.repaint();
		MainPanel.revalidate();
		jLabel1.setText("view");
		MainPanel.add(dataPanel);
		MainPanel.repaint();
		MainPanel.revalidate();
		try {
			Memo model = tabelModel.get(tableMemoView.getSelectedRow());
			txtId.setText(model.getIdMemo() + "");
			txtMapel.setText(model.getMataPelajaran());
			txtTugas.setText(model.getTugas());
			tanggalChooser.setDate(model.getTanggal());
		} catch (IndexOutOfBoundsException exception) {
		}
		txtMapel.setEditable(false);
		txtTugas.setEditable(false);
		
		btnSave.setVisible(false);
		
        }//GEN-LAST:event_btnViewActionPerformed
	//panel profile
        private void ProfileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProfileButtonActionPerformed
                // TODO add your handling code here:
		MainPanel.removeAll();
		MainPanel.repaint();
		MainPanel.revalidate();
		
		if (!user.getJabatan().equals("Wali")){
			btnAdd.setVisible(false);
			btnUpdate.setVisible(false);
			btnDelete.setVisible(false);
			adminAdd.setVisible(false);
			adminUpdate.setVisible(false);
			adminDelete.setVisible(false);
			MainPanel.add(UserPanel);
			try {	
				nisnF.setText(user.getNisn()+"");
				usernameF.setText(user.getUsername());
				passwordF.setText(user.getPassword());
				namaF.setText(user.getNama());
				noabsenF.setText(user.getNoAbsen());
				notelpF.setText(user.getNoTelp());
				jabatanF.setText(user.getJabatan());
				btnOk.setVisible(false);
				image = (Image) ImageIO.read(new BufferedInputStream(user.getFoto()));
				if (img == null)
					img = new ImageIcon(image);
				lblImage.setIcon(img);
			} catch (IndexOutOfBoundsException exception) {
			} catch (IOException ex) {
				Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else
			MainPanel.add(AdminPanel);
		MainPanel.repaint();
		MainPanel.revalidate();
        }//GEN-LAST:event_ProfileButtonActionPerformed
	//add profile
        private void adminAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminAddActionPerformed
                // TODO add your handling code here:
		
		MainPanel.removeAll();
		MainPanel.repaint();
		MainPanel.revalidate();
		jLabel13.setText("add");
		MainPanel.add(UserPanel);
		MainPanel.repaint();
		MainPanel.revalidate();
		txtMapel.setText("");
		txtTugas.setText("");
		nisnF.setText("");
		usernameF.setText("");
		passwordF.setText("");
		namaF.setText("");
		noabsenF.setText("");
		notelpF.setText("");
		jabatanF.setText("");
		lblImage.setIcon(null);
		btnOk.setVisible(true);
        }//GEN-LAST:event_adminAddActionPerformed
	//update profile
        private void adminUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminUpdateActionPerformed
                // TODO add your handling code here:
		
		MainPanel.removeAll();
		MainPanel.repaint();
		MainPanel.revalidate();
		jLabel13.setText("update");
		MainPanel.add(UserPanel);
		MainPanel.repaint();
		MainPanel.revalidate();
		try {	
			User model = tabelModelUser.get(userTable.getSelectedRow());
			nisnF.setText(model.getNisn()+"");
			usernameF.setText(model.getUsername());
			passwordF.setText(model.getPassword());
			namaF.setText(model.getNama());
			noabsenF.setText(model.getNoAbsen());
			notelpF.setText(model.getNoTelp());
			jabatanF.setText(model.getJabatan());
			image = (Image) ImageIO.read(new BufferedInputStream(model.getFoto()));
			img = new ImageIcon(image);
			lblImage.setIcon(img);
		} catch (IndexOutOfBoundsException exception) {
		} catch (IOException ex) {
			Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
		}
		btnOk.setVisible(true);
        }//GEN-LAST:event_adminUpdateActionPerformed
	//hapus profile
        private void adminDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminDeleteActionPerformed
                // TODO add your handling code here:
		User model = tabelModelUser.get(userTable.getSelectedRow());
		nisnF.setText(model.getNisn()+"");
		controllerUser.deleteUser(this);
        }//GEN-LAST:event_adminDeleteActionPerformed
	//view profile
        private void adminViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminViewActionPerformed
                // TODO add your handling code here:
		                          
                // TODO add your handling code here:
		MainPanel.removeAll();
		MainPanel.repaint();
		MainPanel.revalidate();
		jLabel13.setText("view");
		MainPanel.add(UserPanel);
		MainPanel.repaint();
		MainPanel.revalidate();
		try {	
			User model = tabelModelUser.get(userTable.getSelectedRow());
			nisnF.setText(model.getNisn()+"");
			usernameF.setText(model.getUsername());
			passwordF.setText(model.getPassword());
			namaF.setText(model.getNama());
			noabsenF.setText(model.getNoAbsen());
			notelpF.setText(model.getNoTelp());
			jabatanF.setText(model.getJabatan());
			image = (Image) ImageIO.read(new BufferedInputStream(model.getFoto()));
			img = new ImageIcon(image);
			lblImage.setIcon(img);
		} catch (IndexOutOfBoundsException exception) {
		} catch (IOException ex) {
			Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
		}
		btnOk.setVisible(false);
			
        }//GEN-LAST:event_adminViewActionPerformed
	//tombol ok
        private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
                // TODO add your handling code here:
                if (jLabel13.getText().equals("add"))
			try {
				controllerUser.insertUser(this);
		} catch (IOException ex) {
			Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
		}
                else
			try {
				controllerUser.updateUser(this);
		} catch (IOException ex) {
			Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
		}
		
        }//GEN-LAST:event_btnOkActionPerformed
	//tombol logout
        private void LogoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutButtonActionPerformed
                // TODO add your handling code here:
		user = null;
		this.setVisible(false);
		login = new LoginFrame();
		login.setVisible(true);
        }//GEN-LAST:event_LogoutButtonActionPerformed
	//upload image
        private void btnUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadActionPerformed
                // TODO add your handling code here:
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		File f = chooser.getSelectedFile();
		String filename = f.getAbsolutePath();
		ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(filename));
		setImage(icon.getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH));
		img = new ImageIcon(image);
		lblImage.setIcon(img);
        }//GEN-LAST:event_btnUploadActionPerformed
	//panel about
        private void AboutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutButtonActionPerformed
                // TODO add your handling code here:
		MainPanel.removeAll();
		MainPanel.repaint();
		MainPanel.revalidate();
		
		MainPanel.add(AboutPanel);
		MainPanel.repaint();
		MainPanel.revalidate();
        }//GEN-LAST:event_AboutButtonActionPerformed
	//panel struktur organisasi kelas
        private void SokButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SokButtonActionPerformed
		// TODO add your handling code here:
		MainPanel.removeAll();
		MainPanel.repaint();
		MainPanel.revalidate();
		
		//mengambil foto dari database
		if (notLoadedSok){
			for (int i = 0; i < 5; i++) {
				try {
					image = (Image) ImageIO.read(new BufferedInputStream(modelSok.get(i).getFoto())).getScaledInstance(labelWaliKelas.getWidth(), labelWaliKelas.getHeight(), Image.SCALE_SMOOTH);
					fotoList[i] = new ImageIcon(image);
				} catch (IOException ex) {
					Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
			notLoadedSok = false;
		}
		
		//menampilkan username dan foto 
		labelWaliKelas.setIcon(fotoList[0]);
		labelKetuaKelas.setIcon(fotoList[1]);
		labelWakilKetua.setIcon(fotoList[2]);
		labelSekretasi.setIcon(fotoList[3]);
		labelBendahara.setIcon(fotoList[4]);
		namaWaliKelas.setText((modelSok.get(0)).getUsername());
		namaKetuaKelas.setText((modelSok.get(1)).getUsername());
		namaWakilKetua.setText((modelSok.get(2)).getUsername());
		namaSekretaris.setText((modelSok.get(3)).getUsername());
		namaBendahara.setText((modelSok.get(4)).getUsername());
		
		MainPanel.add(SokPanel);
		MainPanel.repaint();
		MainPanel.revalidate();
        }//GEN-LAST:event_SokButtonActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
        //</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrame().setVisible(true);
			}
		});
	
		
	}
	

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton AboutButton;
        private javax.swing.JPanel AboutPanel;
        private javax.swing.JPanel AdminPanel;
        private javax.swing.JPanel AllPanel;
        private javax.swing.JButton HomeButton;
        private javax.swing.JPanel HomePanel;
        private javax.swing.JButton LogoutButton;
        private javax.swing.JPanel MainPanel;
        private javax.swing.JPanel MenuPanel;
        private javax.swing.JButton ProfileButton;
        private javax.swing.JButton SokButton;
        private javax.swing.JPanel SokPanel;
        private javax.swing.JButton TugasButton;
        private javax.swing.JPanel TugasPanel;
        private javax.swing.JPanel UserPanel;
        private javax.swing.JButton adminAdd;
        private javax.swing.JButton adminDelete;
        private javax.swing.JButton adminUpdate;
        private javax.swing.JButton adminView;
        private javax.swing.JButton btnAdd;
        private javax.swing.JButton btnDelete;
        private javax.swing.JButton btnOk;
        private javax.swing.JButton btnSave;
        private javax.swing.JButton btnUpdate;
        private javax.swing.JButton btnUpload;
        private javax.swing.JButton btnView;
        private javax.swing.JPanel dataPanel;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel10;
        private javax.swing.JLabel jLabel11;
        private javax.swing.JLabel jLabel12;
        private javax.swing.JLabel jLabel13;
        private javax.swing.JLabel jLabel14;
        private javax.swing.JLabel jLabel15;
        private javax.swing.JLabel jLabel16;
        private javax.swing.JLabel jLabel17;
        private javax.swing.JLabel jLabel18;
        private javax.swing.JLabel jLabel19;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel21;
        private javax.swing.JLabel jLabel23;
        private javax.swing.JLabel jLabel24;
        private javax.swing.JLabel jLabel25;
        private javax.swing.JLabel jLabel26;
        private javax.swing.JLabel jLabel27;
        private javax.swing.JLabel jLabel28;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JLabel jLabel8;
        private javax.swing.JLabel jLabel9;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JScrollPane jScrollPane3;
        private javax.swing.JScrollPane jScrollPane5;
        private javax.swing.JSeparator jSeparator1;
        private javax.swing.JSeparator jSeparator2;
        private javax.swing.JSeparator jSeparator3;
        private javax.swing.JSeparator jSeparator4;
        private javax.swing.JSeparator jSeparator5;
        private javax.swing.JSeparator jSeparator7;
        private javax.swing.JTextField jabatanF;
        private javax.swing.JLabel labelBendahara;
        private javax.swing.JLabel labelKetuaKelas;
        private javax.swing.JLabel labelSekretasi;
        private javax.swing.JLabel labelWakilKetua;
        private javax.swing.JLabel labelWaliKelas;
        private javax.swing.JLabel lblId;
        private javax.swing.JLabel lblImage;
        private javax.swing.JLabel lblMapel;
        private javax.swing.JLabel lblTugas;
        private javax.swing.JLabel lblTugas1;
        private javax.swing.JLabel namaBendahara;
        private javax.swing.JTextField namaF;
        private javax.swing.JLabel namaKetuaKelas;
        private javax.swing.JLabel namaSekretaris;
        private javax.swing.JLabel namaWakilKetua;
        private javax.swing.JLabel namaWaliKelas;
        private javax.swing.JTextField nisnF;
        private javax.swing.JTextField noabsenF;
        private javax.swing.JTextField notelpF;
        private javax.swing.JTextField passwordF;
        private javax.swing.JTable tableMemoView;
        private com.toedter.calendar.JDateChooser tanggalChooser;
        private javax.swing.JTextField txtId;
        private javax.swing.JTextField txtMapel;
        private javax.swing.JTextArea txtTugas;
        private javax.swing.JTable userTable;
        private javax.swing.JTextField usernameF;
        // End of variables declaration//GEN-END:variables
	//jalankan ketika memo berubah
	@Override
	public void OnMemoChange(MemoModel model) {
		txtId.setText(model.getId()+"");
		txtMapel.setText(model.getMatapelajaran());
		txtTugas.setText(model.getTugas());
		tanggalChooser.setDate(model.getTanggal());
	}
	//jalankan ketika memo baru tersimpan
	@Override
	public void OnMemoInsert(Memo memo) {
		tabelModel.add(memo);
	}
	//jalankan ketika memo terhapus
	@Override
	public void OnMemoDelete() {
		int index = tableMemoView.getSelectedRow();
		tabelModel.remove(index);
	}
	//jalankan ketika memo terupdate
	@Override
	public void OnMemoUpdate(Memo memo) {
		int index = tableMemoView.getSelectedRow();
		tabelModel.set(index, memo);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		try {
			
			Memo model = tabelModel.get(tableMemoView.getSelectedRow());
			txtId.setText(model.getIdMemo() + "");
			txtMapel.setText(model.getMataPelajaran());
			txtTugas.setText(model.getTugas());
			tanggalChooser.setDate(model.getTanggal());
			User umodel = tabelModelUser.get(userTable.getSelectedRow());
			nisnF.setText(umodel.getNisn()+"");
		} catch (IndexOutOfBoundsException exception) {
		}
	}

	//load database awal
	public void loadDatabase() throws SQLException, MemoException, UserException{
		MemoDao memoDao = KapapaDatabase.getMemoDao();
		UserDao userDao = KapapaDatabase.getUserDao();
		tabelModel.setList(memoDao.selectAllMemo());
		tabelModelUser.setList(userDao.selectAllUser());
		modelSok.setList(userDao.selectSOK());
	}
	//jalankan ketika user berubah
	@Override
	public void OnUserChange(UserModel model) {
		nisnF.setText(modelUser.getNisn()+"");
		usernameF.setText(modelUser.getUsername());
		passwordF.setText(modelUser.getPassword());
		namaF.setText(modelUser.getNama());
		noabsenF.setText(modelUser.getNoAbsen());
		notelpF.setText(modelUser.getNoTelp());
		jabatanF.setText(modelUser.getJabatan());
	}
	//jalankan ketika user baru tersimpan
	@Override
	public void OnUserInsert(User user) {
		tabelModelUser.add(user);
	}
	//jalankan ketika user terhapus
	@Override
	public void OnUserDelete() {
		int index = userTable.getSelectedRow();
		tabelModelUser.remove(index);
	}
	//jalankan ketika user diperbaharui
	@Override
	public void OnUserUpdate(User user) {
		int index = userTable.getSelectedRow();
		tabelModelUser.set(index, user);
	}
}
