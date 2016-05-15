package c.m.system;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SwingTest {
	public final static int MTRX_H = 5;
	public final static int MTRX_W = 10;
	public final static int TXT_F_W = 30;
	public final static int MTRX_W_LONG = TXT_F_W;

	public final static int WINDOW_W = 360;
	public final static int WINDOW_H = 400;
	String as = "[0.9077, 0;\n 0, 0.5363]";
	String bs = "[1;\n 1]";
	String cs = "[1.4491, -1.1315]";
	String ds = "[1]";
	String us = "[0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 ; 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]";
	String dbs = "[0, 1, 1, 1, 1, 1, 1, 1, 1, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5; 0, 1, 1, 1, 1, 1, 1, 1, 1, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5]";

	public static void main(String[] args) {
		JFrame f = new JFrame("DNC");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TextAreaForm form = new SwingTest().new TextAreaForm();
		f.getContentPane().add(form, BorderLayout.NORTH);
		JPanel p = new JPanel();
		f.getContentPane().add(p, BorderLayout.SOUTH);

		f.pack();
		f.setSize(WINDOW_W, WINDOW_H);
		f.setVisible(true);
	}

	class TextAreaForm extends JPanel {

		public TextAreaForm() {
			super(new BorderLayout());
			JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
			add(p);
			p.setPreferredSize(new Dimension(WINDOW_W, WINDOW_H));
			// p.setSize(new Dimension(300, 400));
			JLabel matrixALabel = new JLabel("Matrix A");
			p.add(matrixALabel);
			JTextArea matrixATA = new JTextArea(as, MTRX_H, MTRX_W);
			p.add(matrixATA);
			JLabel matrixBLabel = new JLabel("Matrix B");
			p.add(matrixBLabel);
			JTextArea matrixBTA = new JTextArea(bs, MTRX_H, MTRX_W);
			p.add(matrixBTA);
			JLabel cl = new JLabel("Matrix C");
			p.add(cl);
			JTextArea c = new JTextArea(cs, MTRX_H, MTRX_W);
			p.add(c);
			JLabel dl = new JLabel("Matrix D");
			p.add(dl);
			JTextArea d = new JTextArea(ds, MTRX_H, MTRX_W);
			p.add(d);
			JLabel ul = new JLabel("U");
			p.add(ul);
			JTextField u = new JTextField(us, MTRX_W_LONG);
			p.add(u);
			JLabel dbl = new JLabel("DTRB. U");
			p.add(dbl);
			JTextField db = new JTextField(dbs, MTRX_W_LONG);
			p.add(db);
			
			//p.add(submitPane);
		}

	}
}
