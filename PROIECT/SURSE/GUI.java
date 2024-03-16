
import Users.*;
import Catalog.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;


public class GUI extends JFrame implements ActionListener {

    JButton sButton,tButton,pButton,aButton;
    JPanel buttonPanel,textPanel,rightPanel,leftPanel, mainPanel;
    JTextField textField;

    JScrollPane scrollPane;

    Catalog catalog;
     GUI(Catalog catalog){
    super("Catalog");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800,800);
    this.catalog = catalog;

    sButton = new JButton("Student Page");
    tButton = new JButton("Teacher Page");
    pButton = new JButton("Parent Page");
    aButton = new JButton("Assistant Page");
    sButton.setBackground(new Color(204,255,229));
    tButton.setBackground(new Color(204,255,229));
    pButton.setBackground(new Color(204,255,229));
    aButton.setBackground(new Color(204,255,229));
    buttonPanel = new JPanel(new GridLayout(1,3));
    buttonPanel.add(sButton);
    buttonPanel.add(tButton);
    buttonPanel.add(aButton);
    buttonPanel.add(pButton);

    sButton.addActionListener(this);
    tButton.addActionListener(this);
    pButton.addActionListener(this);
    aButton.addActionListener(this);

    textPanel = new JPanel();
    textField = new JTextField(40);
    textPanel.add(textField);

    mainPanel = new JPanel(new BorderLayout());
    rightPanel = new JPanel();
    leftPanel = new JPanel();


    mainPanel.add(rightPanel,BorderLayout.EAST);
    mainPanel.add(leftPanel,BorderLayout.WEST);
    mainPanel.add(textPanel, BorderLayout.NORTH);

    this.setLayout(new BorderLayout());
    add(buttonPanel,BorderLayout.NORTH);
    add(mainPanel,BorderLayout.CENTER);


    setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==sButton){
            leftPanel.removeAll();
            rightPanel.removeAll();
            makeStudentPage();
        }
        else if(e.getSource()==tButton){
            leftPanel.removeAll();
            rightPanel.removeAll();
            makeTeacherPage();
        }
        else if(e.getSource()==pButton){
            leftPanel.removeAll();
            rightPanel.removeAll();
            makeParentPage();
        }
        else if(e.getSource()==aButton){
            leftPanel.removeAll();
            rightPanel.removeAll();
            makeAssistantPage();
        }
    }

    public void makeStudentPage(){

         String studentName = textField.getText();
        List<Course> courseList = catalog.getCourseList();
        List<Course> studentCourses = new ArrayList<>();
        List<String> courseNames = new ArrayList<>();
        String data[];
        boolean found = false;
         for(Course course: courseList){
             HashMap<Student, Grade> studentList = course.getAllStudentGrades();
             for(Student student: studentList.keySet()){
                 String fullName = student.getFirstName() +" " +  student.getLastName();

                 if(fullName.equalsIgnoreCase(studentName)){
                     studentCourses.add(course);
                     found=true;
                     break;
                 }
             }
         }
         if(!found){
             System.out.println(studentName + " nu este inmatriculat in catalog");
         }
         else{
             for(Course course: studentCourses){
                 courseNames.add(course.getNume());
             }
             data = new String[courseNames.size()];
             courseNames.toArray(data);

             if (scrollPane != null) {
                 leftPanel.remove(scrollPane);
             }
             JList<String> jlist = new JList<>(data);
             scrollPane = new JScrollPane(jlist);
             jlist.addListSelectionListener(new ListSelectionListener() {

                 @Override
                 public void valueChanged(ListSelectionEvent e) {
                     rightPanel.removeAll();
                     String selectedValue = jlist.getSelectedValue();

                     for(Course course: courseList){
                         if(selectedValue.equals(course.getNume())){
                             rightPanel.removeAll();
                             JPanel tpanel = new JPanel();
                             JPanel apanel = new JPanel();
                             JPanel infoPanel = new JPanel(new GridLayout(0,1));
                             infoPanel.add(tpanel);
                             infoPanel.add(apanel);
                             rightPanel.add(infoPanel, BorderLayout.WEST);
                             String teacher = course.getpTitular().getFirstName() + " " + course.getpTitular().getLastName();
                             JLabel label1 = new JLabel("Teacher: " + teacher);
                             rightPanel.add(label1);
                             List<Assistant> alist = course.getAsList();
                             String[] str = new String[30];
                             int index = 0;
                             infoPanel.add(new JLabel("Assistants: "));
                             for(Assistant assistant: alist){
                                 str[index] = assistant.getFirstName() + " " + assistant.getLastName();
                                 infoPanel.add(new JLabel(str[index++]));
                             }
                             JLabel l3 = new JLabel("Asistentul corespunzator: ");
                             infoPanel.add(l3);
                             List<Group> groupList = course.getGroup();
                             boolean stop = false;
                             for(int i = 0; i <groupList.size(); i++){
                                 Group g = groupList.get(i);
                                 List<Student> gStud = g.sList();
                                 for(Student student: gStud){
                                     String nAux = student.getFirstName() + " " + student.getLastName();
                                     if(nAux.equalsIgnoreCase(studentName)){
                                         JLabel label = new JLabel(g.getAssistant().getFirstName() + " " + g.getAssistant().getLastName());
                                         infoPanel.add(label);
                                         stop=true;
                                         break;
                                     }
                                 }
                                 if(stop){
                                     break;
                                 }
                             }

                             HashMap<Student,Grade> sGrades = course.getAllStudentGrades();
                             JLabel glabel = new JLabel("Notele studentului: ");
                             infoPanel.add(glabel);
                           for (Map.Entry<Student, Grade> entry : sGrades.entrySet()) {
                                Student key = entry.getKey();
                                 Grade value = entry.getValue();
                                 String nAux = key.getFirstName() + " " + key.getLastName();
                                 if(nAux.equalsIgnoreCase(studentName)){

//
                                     if(value.getPartialScore()==null && value.getExamScore()!=null){
                                         String s1 ="Nota din examen: " + value.getExamScore();

                                     JLabel l1 = new JLabel(s1);
                                     infoPanel.add(l1);
                                     break;
                                     }

                                  else if(value.getExamScore()==null && value.getPartialScore()!=null){

                                         String s2 ="Nota de pe parcurs: " + value.getPartialScore();

                                         JLabel l1 = new JLabel("Nota pe parcurs: " );
                                         infoPanel.add(l1);
                                         break;
                                     }
                                 }


                             }

                            rightPanel.add(infoPanel);

                             break;
                         }
                     }

                 //    rightPanel.removeAll();
//                     rightPanel.add(textArea);
                     rightPanel.validate();
                 }
             });
             leftPanel.add(scrollPane);
             leftPanel.revalidate();

         }



     }

    public void makeTeacherPage(){
        ScoreVisitor scoreVisitor = new ScoreVisitor();
        String teacherName = textField.getText();
        JPanel coursePanel = new JPanel();
        JPanel gradesPanel = new JPanel();
        DefaultListModel listModel = new DefaultListModel();
        JList list = new JList(listModel);
        gradesPanel.add(list);

        leftPanel.add(coursePanel);
        rightPanel.add(gradesPanel);
        JButton jb = new JButton("VALIDARE NOTA");
        jb.setBackground(new Color(204,35,229));
        mainPanel.add(jb,BorderLayout.SOUTH);

        for(Course course: catalog.getCourseList()){
            String name = course.getpTitular().getFirstName() + " " + course.getpTitular().getLastName();
            if(name.equalsIgnoreCase(teacherName)){
                    Label l = new Label(course.getNume());
                    coursePanel.add(l);
                    HashMap<Student,Grade> sg = course.getAllStudentGrades();
                for (Map.Entry<Student, Grade> entry : sg.entrySet()) {
                    Student key = entry.getKey();
                    Grade value = entry.getValue();
                    String sNota = key.getFirstName() + " " + key.getLastName() + " " + Double.toString(value.getExamScore());
                    listModel.addElement(sNota);
                }
                }
            }
        }

    public void makeAssistantPage(){

        ScoreVisitor scoreVisitor = new ScoreVisitor();
        String asName = textField.getText();
        JPanel coursePanel = new JPanel();
        JPanel gradesPanel = new JPanel();
        DefaultListModel listModel = new DefaultListModel();
        JList list = new JList(listModel);
        gradesPanel.add(list);
        leftPanel.add(coursePanel);
        rightPanel.add(gradesPanel);
        JButton jb = new JButton("VALIDARE NOTA");
        jb.setBackground(new Color(204,35,229));
        mainPanel.add(jb,BorderLayout.SOUTH);

        for(Course course: catalog.getCourseList()) {
            List<Assistant> assistants = course.getAsList();

            for(Assistant assistant : assistants){
                String nume = assistant.getFirstName() + " " + assistant.getLastName();
                if(nume.equalsIgnoreCase(asName)){
                JLabel l = new JLabel(course.getNume());
                coursePanel.add(l);
                    HashMap<Student,Grade> sg = course.getAllStudentGrades();

                    for (Map.Entry<Student, Grade> entry : sg.entrySet()) {
                        Student key = entry.getKey();
                        Grade value = entry.getValue();
                        String sNota = key.getFirstName() + " " + key.getLastName() + " " + Double.toString(value.getPartialScore());
                        listModel.addElement(sNota);
                    }
                }
            }


        }



    }

    public void makeParentPage(){
         leftPanel.removeAll();
        String parentName = textField.getText();
        JPanel p = new JPanel(new GridLayout(0,1));
        boolean found = false;
        for(Course course: catalog.getCourseList()){
            List<Student> students = course.getAllStudents();
            for(Student student: students){
                System.out.println(student.getFirstName()+ " " + student.getLastName());
                String momName;
                String dadName;
                if(student.getMother()==null) {
                    momName = " ";
                }
                else momName = student.getMother().getFirstName() + " " + student.getMother().getLastName();
                if(student.getFather()==null) {
                    dadName = " ";
                }
                else dadName = student.getFather().getFirstName() + " " + student.getFather().getLastName();
                List<String> notifies;
                if (momName.equalsIgnoreCase(parentName)) {
                    found = true;
                    notifies = student.getMother().notifies(student.getMother());
                    for(String s: notifies){
                        JLabel jLabel = new JLabel(s);
                        p.add(jLabel);
                        System.out.println(s);
                    }

                    break;
                }
                if(dadName.equalsIgnoreCase(parentName)){
                    notifies = student.getFather().notifies(student.getFather());
                    for(String s: notifies){
                        JLabel jLabel = new JLabel(s);
                        p.add(jLabel);
                    }
                    found=true;
                    break;
                }
            }
            if(found)
                break;
        }
        leftPanel.add(p);
    }




}
