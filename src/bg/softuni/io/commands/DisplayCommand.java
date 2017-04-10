package bg.softuni.io.commands;

import bg.softuni.annotations.Alias;
import bg.softuni.annotations.Inject;
import bg.softuni.contracts.Course;
import bg.softuni.contracts.SimpleOrderedBag;
import bg.softuni.exceptions.InvalidInputException;
import bg.softuni.io.IOManager;
import bg.softuni.io.OutputWriter;
import bg.softuni.judge.Tester;
import bg.softuni.models.CourseImpl;
import bg.softuni.models.StudentImpl;
import bg.softuni.network.DownloadManager;
import bg.softuni.repository.StudentsRepository;
import com.sun.corba.se.spi.activation.Repository;

import java.util.Comparator;

@Alias("display")
public class DisplayCommand extends Command {

    @Inject
    private StudentsRepository repository;

    public DisplayCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();

        if(data.length < 3){
            throw new InvalidInputException(this.getInput());
        }

        String entityToDisplay = data[1];
        String sortType = data[2];
        if(entityToDisplay.equalsIgnoreCase("students")){
            Comparator<StudentImpl> studentComparator = (Comparator<StudentImpl>) this.createComparator(sortType);

            SimpleOrderedBag<StudentImpl> list = this.repository.getAllStudentsSorted(studentComparator);

            OutputWriter.writeMessageOnNewLine(list.joiWith(System.lineSeparator()));
        }else if(entityToDisplay.equalsIgnoreCase("courses")){
            Comparator<CourseImpl> courseComparator = (Comparator<CourseImpl>) this.createComparator(sortType);

            SimpleOrderedBag<CourseImpl> list = this.repository.getAllCoursesSorted(courseComparator);

            OutputWriter.writeMessageOnNewLine(list.joiWith(System.lineSeparator()));
        }else{
            throw new InvalidInputException(this.getInput());
        }
    }

    private Comparator<? extends Comparable> createComparator(String sortType) {
        if(sortType.toLowerCase().equals("ascending")){
            return (o1,o2) -> o1.compareTo(o2);
        }else if(sortType.toLowerCase().equals("descending")){
            return (o1,o2) -> o2.compareTo(o1);
        }else{
            throw new InvalidInputException(this.getInput());
        }
    }
}
