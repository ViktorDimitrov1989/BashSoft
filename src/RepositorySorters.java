import java.util.*;

public class RepositorySorters {

    public static void printSortedStudents(
            HashMap<String, ArrayList<Integer>> courseData,
            String comparisonType,
            int numberOfStudents){
        ArrayList<Map.Entry<String,ArrayList<Integer>>> sortedStudents = new ArrayList<>();
        sortedStudents.addAll(courseData.entrySet());

        Collections.sort(sortedStudents, createComparator(comparisonType));
        for (Map.Entry<String, ArrayList<Integer>> student : sortedStudents) {
            OutputWriter.printStudent(student.getKey(), student.getValue());
        }
    }

    private static Comparator<Map.Entry<String, ArrayList<Integer>>> createComparator(String comparisonType){
                switch (comparisonType){
                    case "ascending":
                        return new Comparator<Map.Entry<String, ArrayList<Integer>>>() {
                            @Override
                            public int compare(Map.Entry<String, ArrayList<Integer>> firstStudent,
                                               Map.Entry<String, ArrayList<Integer>> secondStudent) {
                                Double firstStudentTotal = getTotalScore(firstStudent.getValue());
                                Double secondStudentTotal = getTotalScore(secondStudent.getValue());

                                return firstStudentTotal.compareTo(secondStudentTotal);
                            }
                        };
                    case "descending":
                        return new Comparator<Map.Entry<String, ArrayList<Integer>>>() {
                            @Override
                            public int compare(Map.Entry<String, ArrayList<Integer>> firstStudent,
                                               Map.Entry<String, ArrayList<Integer>> secondStudent) {
                                Double firstStudentTotal = getTotalScore(firstStudent.getValue());
                                Double secondStudentTotal = getTotalScore(secondStudent.getValue());

                                return secondStudentTotal.compareTo(firstStudentTotal);
                            }
                        };
                        default:
                            return null;
                }
    }

    private static Double getTotalScore(ArrayList<Integer> grades) {
        Double total = 0.0d;
        for (Integer integer : grades) {
            total += integer;
        }
        return total / grades.size();
    }

    public static void printOrderedStudents(String course, String compareType, Integer numberOfStudents){
        if(course == null){
            OutputWriter.writeMessageOnNewLine(ExceptionMessages.NON_EXISTENT_COURSE);
            return;
        }
        if(numberOfStudents == null){
            numberOfStudents = StudentsRepository.studentsByCourse.get(course).size();
        }
        RepositorySorters.printSortedStudents(StudentsRepository.studentsByCourse.get(course),compareType,numberOfStudents);

    }
}
