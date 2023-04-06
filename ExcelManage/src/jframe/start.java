package jframe;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;


import model.SocketDAO;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Component;

public class start extends JFrame{
	
	private SocketDAO dao = new SocketDAO();
	
	//dialog
	
	//panel1
	private JPanel panel1;
	private JTable table;
	
	private JPanel panel2;
	private Object contents[][];
	
	private static EmbeddedMediaPlayerComponent component;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("test");
					start window = new start();
					window.setVisible(true);
					//component.mediaPlayer().media().play("rtsp://admin:xodidrhkdakstp1!@192.168.0.129:554/trackID=3");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public start() {
		
		//dao.testdb();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 287);
		setTitle("CCTV 매니저");
		setSize(1600,900);
		setResizable(false);
		setLocationRelativeTo(null);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		getContentPane().add(mainPanel);
		
		createMenu();
		itemTabPanel1();
		itemTabPanel2();
		JTabbedPane tabPane = new JTabbedPane();
		tabPane.addTab("CCTV관리", panel1);
		tabPane.addTab("CCTV미리보기", panel2);
		mainPanel.add(tabPane);
		
	}
	
	

	public void itemTabPanel1() {
		panel1 = new JPanel();
		panel1.setBounds(0, 0, 698, 337);
		
		panel1.setLayout(null);
		
		table = new JTable();
		table.setBounds(50, 70, 1484, 680);
		panel1.add(table);
		
		String header[]={"No", "플랫폼", "프로토콜", "CCTV이름", "IP주소", "포트", "사용자아이디", "비밀번호", "카메라 번호"
				,"스트림 저장경로", "녹화 저장경로", "HLS주소", "NGINX 포트", "스트림 화질", "녹화 화질", "스트림 PID", "녹화 PID","메모", "생성일자", "수정일자", "마지막 스트림 시간"};
		contents = null;
		
		
		
		table = new JTable(contents, header);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		table.setModel(new DefaultTableModel(contents, header) {
			public boolean isCellEditable(int i, int c) {
			      return false;
			    }
		});
		resizeColumnWidth();
		JScrollPane jscp1 = new JScrollPane(table);
		
		jscp1.setLocation(203,59);
        jscp1.setSize(1292,698);
	}
	
public void itemTabPanel2() {
	panel2 = new JPanel();
	panel2.setBounds(0, 0, 1584, 861);
	panel2.setLayout(null);
}
	
	public void createMenu() {
		JMenuBar mb = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		mb.add(fileMenu);
		mb.add(editMenu);
		
		this.setJMenuBar(mb);
		
	}
	
	public void resizeColumnWidth() {
		TableColumn l_col;
		final TableColumnModel columnModel = table.getColumnModel();
		 DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for (int i = 0; i < table.getColumnCount(); i++) {
            l_col = table.getColumn(table.getColumnName(i));
            int width = columnHeaderWidth(table, l_col) + 6;

//            l_col.setMinWidth(width);
//            l_col.setMaxWidth(width);
            columnModel.getColumn(i).setWidth(width);
            for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, i);
	            Component comp = table.prepareRenderer(renderer, row, i);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	        }
	        if(width == 40) {
	        	columnModel.getColumn(i).setPreferredWidth(width+10);
	        }
	        else columnModel.getColumn(i).setPreferredWidth(width+20);	
	        columnModel.getColumn(i).setCellRenderer(centerRenderer);
        	}
	}
	
	public int columnHeaderWidth(JTable l_Table, TableColumn col) {
        TableCellRenderer renderer = l_Table.getTableHeader()
                .getDefaultRenderer();

        Component comp = renderer.getTableCellRendererComponent(l_Table,
                col.getHeaderValue(), false, false, 0, 0);
        return comp.getPreferredSize().width;
    }
	
}
