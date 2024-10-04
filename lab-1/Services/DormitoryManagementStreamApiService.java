package Services;

import java.util.*;
import java.util.stream.*;
import Models.Student;

public class DormitoryManagementStreamApiService {
  public static Map<Boolean, List<Student>> splitStudentsByPrivilege(List<Student> students) {
    return students.stream().collect(Collectors.partitioningBy(Student::isPrivileged));
  }

  public static Map<String, List<Student>> groupStudentsByDorms(List<Student> students) {
    return students.stream().collect(Collectors.groupingBy(Student::getDormitory));
  }

  public static Map<Integer, Integer> countStudentsByRooms(List<Student> students) {
    Map<Integer, Long> studentsByRooms = students.stream().collect(Collectors.groupingBy(Student::getRoomNumber, Collectors.counting()));

    return studentsByRooms.entrySet().stream()
    .collect(Collectors.toMap(
        Map.Entry::getKey,
        entry -> entry.getValue().intValue()
    ));
  }

  public static List<Student> sortStudentsByAgeAndPriveleged(List<Student> students) {
    return students.stream().sorted(Comparator.comparing(Student::getAge).thenComparing(Student::isPrivileged)).collect(Collectors.toCollection(LinkedList::new));
  }

  public static Set<Integer> uniqueRoomNumbers(List<Student> students) {
    Set<Integer> studentsRoomNumbers = students.stream().map(Student::getRoomNumber).collect(Collectors.toSet());

    return studentsRoomNumbers;
  }

  public static Optional<Student> findHighestFeeStudent(List<Student> students) {
    return students.stream().max(Comparator.comparing(Student::getFee));
  }
}
