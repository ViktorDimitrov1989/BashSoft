package Repository;

import IO.OutputWriter;
import StaticData.ExceptionMessages;

import java.util.*;
import java.util.stream.Collectors;

public class RepositorySorters {

    public static void printSortedStudents(
            HashMap<String, ArrayList<Integer>> courseData,
            String comparisonType,
            int numberOfStudents){
        Comparator<Map.Entry<String,ArrayList<Integer>>> comparator = createComparator(comparisonType);
        List<String> sortedStudents = courseData.entrySet()
                .stream()
                .sorted(comparator)
                .limit(numberOfStudents)
                .map(x -> x.getKey())
                .collect(Collectors.toList());

        if(comparisonType.equals("descending")){
            Collections.reverse(sortedStudents);
        }

        for (String sortedStudent : sortedStudents) {
            OutputWriter.printStudent(sortedStudent, courseData.get(sortedStudent));
        }

    }

    private static Comparator<Map.Entry<String, ArrayList<Integer>>> createComparator(String comparisonType) {
        Comparator<Map.Entry<String, ArrayList<Integer>>> comparator = (x,y) ->
                Double.compare(x.getValue().stream().mapToDouble(Double::valueOf).average().getAsDouble(),
                        y.getValue().stream().mapToDouble(Double::valueOf).average().getAsDouble());

        return comparator;
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
