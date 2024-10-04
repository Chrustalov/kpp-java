import java.util.*;
import Models.Student;
import Services.DormitoryManagementStreamApiService;

public class Main {
  public static void main(String[] args) {
    List<Student> students = getStudentList();
  
    Map<Boolean, List<Student>> privilegedPartition = DormitoryManagementStreamApiService.splitStudentsByPrivilege(students);
    System.out.println("=== Привілейовані студенти ===");
    printStudentList(privilegedPartition.get(true));
    
    System.out.println("\n=== Не привілейовані студенти ===");
    printStudentList(privilegedPartition.get(false));

    Map<String, List<Student>> dormitoryGroups = DormitoryManagementStreamApiService.groupStudentsByDorms(students);
    System.out.println("\n=== Згруповані студенти за гуртожитками ===");
    printGroupedByDormitory(dormitoryGroups);

    Map<Integer, Integer> countStudentsByRooms = DormitoryManagementStreamApiService.countStudentsByRooms(students);
    System.out.println("\n=== Кількість студентів у кожній кімнаті ===");
    System.out.println(countStudentsByRooms);

    List<Student> sortedStudents = DormitoryManagementStreamApiService.sortStudentsByAgeAndPriveleged(students);
    System.out.println("\n=== Відсортовані студенти за віком та пільгою ===");
    printStudentList(sortedStudents);

    Set<Integer> uniqueRoomNumbers = DormitoryManagementStreamApiService.uniqueRoomNumbers(students);
    System.out.println("\n=== Унікальні номера кімнат ===");
    System.out.println(uniqueRoomNumbers);

    Optional<Student> student = DormitoryManagementStreamApiService.findHighestFeeStudent(students);
    System.out.println("\n=== Студент, який сплачує найбільшу плату за проживання в гуртожиток ===");
    System.out.println(student.orElse(null));
  }

  public static void printStudentList(List<Student> students) {
    if (students == null || students.isEmpty()) {
        System.out.println("Немає студентів у цій категорії.");
    } else {
        students.forEach(System.out::println);
    }
  }
  
  public static void printGroupedByDormitory(Map<String, List<Student>> dormitoryGroups) {
    dormitoryGroups.forEach((dormitory, students) -> {
        System.out.println("Гуртожиток: " + dormitory);
        students.forEach(student -> System.out.println(" - " + student));
        System.out.println();
    });
  }

  public static List<Student> getStudentList() {
    return Arrays.asList(
            new Student("Олексій", "Коваленко", "Гуртожиток 1", 101, 500.0, 20, true),
            new Student("Іван", "Іваненко", "Гуртожиток 1", 102, 450.0, 21, false),
            new Student("Марія", "Петренко", "Гуртожиток 1", 201, 480.0, 19, true),
            new Student("Андрій", "Шевченко", "Гуртожиток 2", 202, 490.0, 22, true),
            new Student("Ольга", "Бондаренко", "Гуртожиток 1", 301, 460.0, 21, false),
            new Student("Юрій", "Мельник", "Гуртожиток 1", 302, 470.0, 20, true),
            new Student("Світлана", "Сидоренко", "Гуртожиток 2", 401, 500.0, 23, false),
            new Student("Тетяна", "Гончар", "Гуртожиток 3", 402, 510.0, 22, true),
            new Student("Дмитро", "Мороз", "Гуртожиток 2", 502, 480.0, 19, false),
            new Student("Наталія", "Захаренко", "Гуртожиток 2", 502, 470.0, 21, true),
            new Student("Олександр", "Ковальчук", "Гуртожиток 1", 601, 450.0, 22, false),
            new Student("Катерина", "Ткаченко", "Гуртожиток 3", 602, 440.0, 23, true),
            new Student("Віктор", "Лисенко", "Гуртожиток 3", 602, 460.0, 20, false),
            new Student("Олена", "Кравець", "Гуртожиток 3", 702, 430.0, 21, true),
            new Student("Ірина", "Демченко", "Гуртожиток 2", 801, 420.0, 22, false)
        );
  }
}
