package Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Client_UI extends JFrame implements ActionListener {
    private String directory;
    private String filename;

    private FileDialog filedialog; // 불러오기 창

    public void setDirectory(String directory) {
        this.directory = directory;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public String getDirectory() {
        return this.directory;
    }
    public String getFilename() {
        return this.filename;
    }

    public Client_UI () {
        setTitle("엑셀 캡처 프로그램");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Container c = getContentPane();

        JButton load_excel_btn = new JButton("엑셀 불러오기");

        load_excel_btn.addActionListener(this); // this ...??

        c.add(load_excel_btn);

        setVisible(true);
        setSize(300, 300);
    }
    public static void main(String[] args) {
        Client_UI client_ui = new Client_UI();
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        filedialog = new FileDialog(this, "엑셀 불러오기", FileDialog.LOAD);
        filedialog.setVisible(true);


        System.out.println(filedialog.getDirectory());
        filedialog.setSize(300, 200);

        notifyAll();
        //System.out.println(filedialog.getDirectory());
        //System.out.println(filedialog.getFile());
    }
}
