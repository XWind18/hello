/*
 * ServerFrame.java
 *
 * Created on __DATE__, __TIME__
 */

package hello.server.ui;

import hello.Servercore.Server;
import hello.common.TranObject;
import hello.dao.MemberDao;

/**
 *
 * @author  __USER__
 */
public class ServerFrame extends javax.swing.JFrame {

	/** Creates new form ServerFrame */
	public ServerFrame() {
		initComponents();
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		panelSidebar = new javax.swing.JPanel();
		panelInfo = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		txtInfo = new javax.swing.JTextArea();
		panelbtn = new javax.swing.JPanel();
		btnQuery = new javax.swing.JButton();
		btnDelete = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Hello Server");

		javax.swing.GroupLayout panelSidebarLayout = new javax.swing.GroupLayout(
				panelSidebar);
		panelSidebar.setLayout(panelSidebarLayout);
		panelSidebarLayout.setHorizontalGroup(panelSidebarLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 97, Short.MAX_VALUE));
		panelSidebarLayout.setVerticalGroup(panelSidebarLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 454, Short.MAX_VALUE));

		txtInfo.setColumns(20);
		txtInfo.setRows(5);
		jScrollPane1.setViewportView(txtInfo);

		javax.swing.GroupLayout panelInfoLayout = new javax.swing.GroupLayout(
				panelInfo);
		panelInfo.setLayout(panelInfoLayout);
		panelInfoLayout.setHorizontalGroup(panelInfoLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				panelInfoLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jScrollPane1,
								javax.swing.GroupLayout.DEFAULT_SIZE, 370,
								Short.MAX_VALUE).addGap(15, 15, 15)));
		panelInfoLayout.setVerticalGroup(panelInfoLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				panelInfoLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jScrollPane1,
								javax.swing.GroupLayout.DEFAULT_SIZE, 326,
								Short.MAX_VALUE).addContainerGap()));

		btnQuery.setText("\u67e5\u8be2");
		btnQuery.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnQueryActionPerformed(evt);
			}
		});

		btnDelete.setText("\u5220\u9664");
		btnDelete.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnDeleteActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout panelbtnLayout = new javax.swing.GroupLayout(
				panelbtn);
		panelbtn.setLayout(panelbtnLayout);
		panelbtnLayout.setHorizontalGroup(panelbtnLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				panelbtnLayout.createSequentialGroup().addGap(26, 26, 26)
						.addComponent(btnQuery).addGap(52, 52, 52)
						.addComponent(btnDelete)
						.addContainerGap(205, Short.MAX_VALUE)));
		panelbtnLayout
				.setVerticalGroup(panelbtnLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								panelbtnLayout
										.createSequentialGroup()
										.addContainerGap(34, Short.MAX_VALUE)
										.addGroup(
												panelbtnLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(btnDelete)
														.addComponent(btnQuery))
										.addGap(27, 27, 27)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(panelSidebar,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														panelInfo,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														panelbtn,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addComponent(panelInfo,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(panelbtn,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE))
				.addComponent(panelSidebar,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new DeleteMemberFrame().setVisible(true);
			}
		});
	}

	private void btnQueryActionPerformed(java.awt.event.ActionEvent evt) {
	
	}
	public void showMessage(String message){
		MemberDao dao = new MemberDao();
		String tip= dao.query();
		txtInfo.append(tip);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ServerFrame().setVisible(true);
				
			}

		});
		new Server().start();
		
	}
	public void showQuery (String message){
		
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btnDelete;
	private javax.swing.JButton btnQuery;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JPanel panelInfo;
	private javax.swing.JPanel panelSidebar;
	private javax.swing.JPanel panelbtn;
	private javax.swing.JTextArea txtInfo;
	// End of variables declaration//GEN-END:variables

}