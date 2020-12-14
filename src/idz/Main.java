package idz;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Класс пользовательского интерфейса со свойствами <b>sessions</b>, <b>groups</b> и <b>students</b>.
 * @author Дребезов Денис 6.1217-1
 * вариант 1
*/
public class Main {

    /** Поле список сесий*/
    private static final ArrayList<Session> sessions = new ArrayList<>();
    /** Поле список групп*/
    private static final ArrayList<Group> groups = new ArrayList<>();
    /** Поле список студентов*/
    private static final ArrayList<Student> students = new ArrayList<>();

    /**
     * Функция основного пользовательского интерфейса
     * @param args - параметри получаемые из консоли
     */
    public static void main(String[] args) {
        System.out.println("«Облік   успішності   студентів»");
        System.out.println("ІДЗ з предмету «Кросплатформове програмування»");
        System.out.println("Дребезов Денис 6.1217-1 Варіант 1\n");
        boolean working = true;
        while (working) {
            System.out.println("Оберіть дію");
            System.out.println("1. Керувати студентами");
            System.out.println("2. Керувати групами");
            System.out.println("3. Керувати сесією");
            System.out.println("Якщо хочете выйти напишіть exit");
            Scanner in = new Scanner(System.in);
            switch (in.nextLine()) {
                case "1":
                    studentMenu();
                    break;
                case "2":
                    groupMenu();
                    break;
                case "3":
                    sessionsMenu();
                    break;
                case "exit":
                    working = false;
                    break;
                default:
                    System.out.println("Введіть значення, яке відповідає значенням меню");
            }
        }
    }

    /**
     * Функция работы со студенческим меню
     */
    private static void studentMenu() {
        System.out.println("Оберіть дію");
        System.out.println("1. Створити студента");
        System.out.println("2. Закріпити студента за групою");
        System.out.println("3. Виставити оцінку студенту з предмета");
        System.out.println("4. Вивести оцінки з предметів");
        System.out.println("5. Вивести розклад з сесії");
        Scanner in = new Scanner(System.in);
        switch (in.nextLine()) {
            case "1":
                System.out.println("Введіть ім'я студента");
                students.add(new Student(in.nextLine()));
                break;
            case "2":
                if (students.size() != 0) {
                    System.out.println("Оберіть студента. Для вибору введіть номер студента");
                    for (int i = 0; i < students.size(); i++) {
                        System.out.println(i + ": " + students.get(i).name);
                    }
                    int number = in.nextInt();
                    if (groups.size() != 0) {
                        System.out.println("Оберіть групу в яку призначити студента " + students.get(number).name);
                        for (int i = 0; i < groups.size(); i++) {
                            System.out.println(i + ": " + groups.get(i).name);
                        }
                        int group = in.nextInt();
                        students.get(number).group = groups.get(group);
                        System.out.println("Студента " + students.get(number).name + " закріплено за групою " + students.get(number).group.name);
                    } else {
                        System.out.println("Груп в программі не існує. Спочатку додайте групу");
                        break;
                    }
                } else {
                    System.out.println("Студентів в программі не існує. Спочатку додайте студента");
                }
                break;
            case "3":
                if (students.size() != 0) {
                    System.out.println("Оберіть студента. Для вибору введіть номер студента");
                    for (int i = 0; i < students.size(); i++) {
                        System.out.println(i + ": " + students.get(i).name);
                    }
                    int student = in.nextInt();
                    System.out.println("Оберіть предмет. Для вибору введіть номер предмету");
                    for (int i = 0; i < students.size(); i++) {
                        System.out.println(i + ": " +students.get(student).group.session.subjects.get(i).name);
                    }
                    int subject = in.nextInt();
                    System.out.println("Введіть оцінку з предмету "+students.get(student).group.session.subjects.get(subject).name);
                    int mark = in.nextInt();
                    students.get(0).addMark(students.get(student).group.session.subjects.get(subject),mark);

                } else {
                    System.out.println("Студентів в программі не існує. Спочатку додайте студента");
                }
                break;
            case "4":
                if (students.size() != 0) {
                    System.out.println("Оберіть студента. Для вибору введіть номер студента");
                    for (int i = 0; i < students.size(); i++) {
                        System.out.println(i + ": " + students.get(i).name);
                    }
                    int student = in.nextInt();
                    for (SubjectMark result: students.get(student).marks) {
                        System.out.println(result.subject.name +": "+result.mark);
                    }
                } else {
                    System.out.println("Студентів в программі не існує. Спочатку додайте студента");
                }
                break;
            case "5":
                if (students.size() != 0) {
                    System.out.println("Оберіть студента. Для вибору введіть номер студента");
                    for (int i = 0; i < students.size(); i++) {
                        System.out.println(i + ": " + students.get(i).name);
                    }
                    int student = in.nextInt();
                    students.get(student).group.session.printSubjects();
                } else {
                    System.out.println("Студентів в программі не існує. Спочатку додайте студента");
                }
                break;
            default:
                System.out.println("Введіть значення, яке відповідає значенням меню");
        }
    }

    /**
     * Функция работы с меню групп
     */
    private static void groupMenu() {
        System.out.println("Оберіть дію");
        System.out.println("1. Додати групу");
        System.out.println("2. Додати сесію до групи");
        System.out.println("3. Вивести розклад сесії групи");
        System.out.println("4. Вивести перелік групи");
        Scanner in = new Scanner(System.in);
        switch (in.nextLine()) {
            case "1":
                System.out.println("Введіть назву групи");
                groups.add(new Group(in.nextLine()));
                break;
            case "2":
                if (groups.size() != 0) {
                    System.out.println("Оберіть групу. Для вибору введіть номер групи");
                    for (int i = 0; i < groups.size(); i++) {
                        System.out.println(i + ": " + groups.get(i).name);
                    }
                    int group = in.nextInt();
                    if (sessions.size() != 0) {
                        System.out.println("Оберіть сесію яку призначити групі" + groups.get(group).name);
                        for (int i = 0; i < sessions.size(); i++) {
                            System.out.println(i + ": " + sessions.get(i).name);
                        }
                        int session = in.nextInt();
                        groups.get(group).session = sessions.get(session);
                    } else {
                        System.out.println("Сесій в программі не існує. Спочатку додайте сесію");
                        break;
                    }
                } else {
                    System.out.println("Груп в программі не існує. Спочатку додайте групу");
                }
                break;
            case "3":
                if (groups.size() != 0) {
                    System.out.println("Оберіть групу. Для вибору введіть номер групи");
                    for (int i = 0; i < groups.size(); i++) {
                        System.out.println(i + ": " + groups.get(i).name);
                    }
                    int group = in.nextInt();
                    groups.get(group).session.printSubjects();
                } else {
                    System.out.println("Груп в программі не існує. Спочатку додайте групу");
                }
                break;
            case "4":
                if (groups.size() != 0) {
                    System.out.println("Оберіть групу. Для вибору введіть номер групи");
                    for (int i = 0; i < groups.size(); i++) {
                        System.out.println(i + ": " + groups.get(i).name);
                    }
                    int groupIndex = in.nextInt();
                    Group group = students.get(groupIndex).group;
                    for (Student student: students
                         ) {
                        if(student.group == group){
                            System.out.println(student.name);
                        }
                    }
                } else {
                    System.out.println("Груп в программі не існує. Спочатку додайте групу");
                }
                break;
            default:
                System.out.println("Введіть значення, яке відповідає значенням меню");
        }
    }

    /**
     * Функция работы с меню сессии
     */
    private static void sessionsMenu() {
        System.out.println("Оберіть дію");
        System.out.println("1. Створити сесію");
        System.out.println("2. Додати предмет для сесії");
        System.out.println("3. Вивести список предметів");
        Scanner in = new Scanner(System.in);
        switch (in.nextLine()) {
            case "1":
                System.out.println("Введіть назву сесії");
                sessions.add(new Session(in.nextLine()));
                break;
            case "2":
                if (sessions.size() != 0) {
                    System.out.println("Оберіть сессію в яку хочете додати залік або екзамен");
                    for (int i = 0; i < sessions.size(); i++) {
                        System.out.println(i + ": " + sessions.get(i).name);
                    }
                    int session = in.nextInt();
                    System.out.println("Введіть назву предмету");
                    String name = in.nextLine();
                    System.out.println("Введіть дату");
                    String date = in.nextLine();
                    System.out.println("Введыть тип (залік / екзамен)");
                    String type = in.nextLine();
                    sessions.get(session).addSubject(name, date, type);
                }
                break;
            case "3":
                if (sessions.size() != 0) {
                    System.out.println("Оберіть сессію яку хочете вивести");
                    for (int i = 0; i < sessions.size(); i++) {
                        System.out.println(i + ": " + sessions.get(i).name);
                    }
                    int session = in.nextInt();
                    for (SessionSubject subject: sessions.get(session).subjects) {
                        System.out.println(subject.type+": "+subject.name+" "+subject.date);
                    }
                }
                break;
            default:
                System.out.println("Введіть значення, яке відповідає значенням меню");
        }
    }
}
/**
 * Класс описывающий сущность студента со свойствами <b>name</b>, <b>group</b> и <b>marks</b>.
 */
class Student {
    /** Поле имя*/
    String name;
    /** Поле группа*/
    Group group;
    /** Поле список оценок*/
    ArrayList<SubjectMark> marks = new ArrayList<>();

    /**
     * Конструктор - создание нового объекта объекта с определенными значениями
     * @param name - имя
     * @see Student#Student(String)
     */
    Student(String name) {
        this.name = name;
    }

    /**
     * Функция для добавления оценки студенту по определенному предмету
     * @param sub - предмет по которому нужно установить оценку
     * @param mark - оценка
     */
    public void addMark(SessionSubject sub, int mark) {
        marks.add(new SubjectMark(sub, mark));
    }
}

/**
 * Класс описывающий сущность группы со свойствами <b>name</b>, <b>session</b>.
 */
class Group {
    /** Поле имя*/
    String name;
    /** Поле сессия*/
    Session session;
    /**
     * Конструктор - создание нового объекта объекта с определенными значениями
     * @param name - имя
     * @see Group#Group(String)
     */
    Group(String name) {
        this.name = name;
    }
}

/**
 * Класс описывающий сущность группы со свойствами <b>name</b>, <b>subjects</b>.
 */
class Session {
    /** Поле имя*/
    public String name;
    /** Поле списка предметов*/
    ArrayList<SessionSubject> subjects = new ArrayList<>();

    /**
     * Конструктор - создание нового объекта объекта с определенными значениями
     * @param a - имя
     * @see Session#Session(String)
     */
    Session(String a) {
        this.name = a;
    }

    /**
     * Функция установки предмета в сессию.
     * @param name - название предмета
     * @param date - дата проведения сессии по предмету
     * @param type - тип зачет или экзамен
     */
    public void addSubject(String name, String date, String type) {
        subjects.add(new SessionSubject(name, date, type));
    }

    /**
     * Функция вывода рассписания сессии.
     */
    public void printSubjects(){
        for (SessionSubject subject: this.subjects) {
            System.out.println(subject.type+": "+subject.name+" "+subject.date);
        }
    }

}

/**
 * Класс описывающий сущность предмета во время сессии со свойствами <b>name</b>, <b>date</b>, <b>type</b>.
 */
class SessionSubject {
    /** Поле имени*/
    String name;
    /** Поле дати*/
    String date;
    /** Поле типа*/
    String type;

    /**
     * Конструктор - создание нового объекта объекта с определенными значениями
     * @param name - название предмета
     * @param date - дата зачета
     * @param type - тип
     * @see SessionSubject#SessionSubject(String, String, String)
     */
    SessionSubject(String name, String date, String type) {
        this.date = date;
        this.name = name;
        this.type = type;
    }
}

/**
 * Класс описывающий оценку студента по предмету сессии со свойствами <b>subject</b>, <b>mark</b>.
 */
class SubjectMark {
    /** Поле хранящее сущность предмета*/
    SessionSubject subject;
    /** Поле оценки*/
    int mark;

    /**
     * Конструктор - создание нового объекта объекта с определенными значениями
     * @param subject - название предмета
     * @param mark - дата зачета
     * @see SubjectMark#SubjectMark(SessionSubject, int)
     */
    public SubjectMark(SessionSubject subject, int mark) {
        this.subject = subject;
        this.mark = mark;
    }
}