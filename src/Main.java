import citizens.Education;
import citizens.Person;
import citizens.Sex;

import java.util.*;
import java.util.stream.Collectors;

import static citizens.Education.HIGHER;
import static citizens.Sex.MAN;
import static citizens.Sex.WOMAN;


public class Main {

    public static final Random RANDOM = new Random();
    public static final int MAJORITY_AGE = 18;
    public static final int MILITARY_AGE = 27;
    public static final int AVERAGE_LIFE_EXPECTANCY = 70;//средняя продолжительность жизни (Россия 2021г.)


    public static void main(String[] args) {

        MAN.setLifeExpectancy(70); //установим продолжительность жизни у мужчин
        WOMAN.setLifeExpectancy(80); //установим продолжительность жизни у женщин
        long populationSize = 10000000; //установим численность населения

        Collection<Person> persons = generatePopulation(populationSize);

        System.out.println("*----------------------Сводный отчет------------------------*");
        long minors = persons.stream()
                .filter(person -> person.getAge() < MAJORITY_AGE)
                .count();
        System.out.printf("Несовершеннолетние, всего: %d\n", minors);

        List<String> recruits = persons.stream()
                .filter(person -> person.getSex() == MAN)
                .filter(person -> person.getAge() >= MAJORITY_AGE)
                .filter(person -> person.getAge() < MILITARY_AGE)
                .map(Person::getSurname)
                .collect(Collectors.toList());

        System.out.printf("Призывники, всего: %d\n", recruits.size());

        List<Person> employers = persons.stream()
                .filter(education -> education.getEducation() == HIGHER)
                .filter(person -> person.getAge() >= MAJORITY_AGE)
                .filter(person -> person.getAge() < MAN.getRetirementAge())
                .filter(Main::exceptWomanRetiredAge)
                .sorted(Comparator.comparing(Person::getSurname))
                .collect(Collectors.toList());

        System.out.printf("Работоспособные,имеющие %s education, всего: %d\n", HIGHER, employers.size());
        System.out.println("*-----------------------------------------------------------*");


    }

    /**
     * Исключить женщин пенсионного возраста.
     *
     * @param person Person
     * @return true/false
     */
    private static boolean exceptWomanRetiredAge(Person person) {
        return !(person.getSex() == WOMAN && person.getAge() >= WOMAN.getRetirementAge());
    }

    /**
     * Генерация случайной популяции.
     *
     * @param uBound размер популяции
     * @return население популяции
     */
    private static Collection<Person> generatePopulation(long uBound) {
        List<String> namesMan = Arrays.asList(
                "Иван",
                "Сергей",
                "Андрей",
                "Петр",
                "Игорь",
                "Александр",
                "Алексей",
                "Илья"
        );
        List<String> surnamesMan = Arrays.asList(
                "Якин",
                "Белов",
                "Крылов",
                "Перов",
                "Малкин",
                "Палкин",
                "Галкин",
                "Залкинд"
        );
        List<String> namesWoman = Arrays.asList(
                "Анна",
                "Ольга",
                "Татьяна",
                "Светлана",
                "Инна",
                "Юлия",
                "Наталья",
                "Мария"
        );
        List<String> surnamesWoman = Arrays.asList(
                "Серова",
                "Белова",
                "Крылова",
                "Щукина",
                "Малкина",
                "Палкина",
                "Галкина",
                "Брик");

        Collection<Person> persons = new ArrayList<>();

        for (int i = 0; i < uBound; i++) {
            Sex sex = Sex.values()[RANDOM.nextInt(Sex.values().length)];
            switch (sex) {
                case MAN:
                    persons.add(new Person(
                            namesMan.get(RANDOM.nextInt(namesMan.size())),
                            surnamesMan.get(RANDOM.nextInt(surnamesMan.size())),
                            RANDOM.nextInt(sex.getLifeExpectancy() + 1),
                            sex,
                            Education.values()[RANDOM.nextInt(Education.values().length)])
                    );
                    break;
                case WOMAN:
                    persons.add(new Person(
                            namesWoman.get(RANDOM.nextInt(namesWoman.size())),
                            surnamesWoman.get(RANDOM.nextInt(surnamesWoman.size())),
                            RANDOM.nextInt(sex.getLifeExpectancy() + 1),
                            sex,
                            Education.values()[RANDOM.nextInt(Education.values().length)])
                    );
                    break;
            }
        }
        return persons;
    }


}