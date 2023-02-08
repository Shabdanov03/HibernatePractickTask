package java8;

import java8.config.HibernateConfig;
import java8.entity.Address;
import java8.entity.Country;
import java8.entity.Programmer;
import java8.entity.Project;
import java8.enums.Status;
import java8.service.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(java.lang.String[] args) {

        HibernateConfig.getManagerFactory();

        CountryService countryService = new CountryServiceImpl();
        AddressService addressService = new AddressServiceImpl();
        ProgrammerService programmerService = new ProgrammerServiceImpl();
        ProjectService projectService = new ProjectServiceImpl();


        //========= COUNTRY OBJECTS =========

        Country country = new Country("USA","United States Of America");
        Country country1 = new Country("KYRGYZSTAN","Central Asia");
        Country country2 = new Country("ENGLAND","Great Britain");
        List<Country> countryList = new ArrayList<>(Arrays.asList(country1,country2));
        Country country3 = new Country("RUSSIA","Big country of world");

        //======== ADDRESS OBJECTS =========

        Address address = new Address("Talas","Kirov",16);
        Address address1 = new Address("Bishkek","Chiy",1);
        Address address2 = new Address("Osh","Kara-Suu",15);
        List<Address> addressList = new ArrayList<>(Arrays.asList(address1,address2));
        Address address3 = new Address("Naryn","Chaek",19);

        //========= PROGRAMMER OBJECTS =========

        Programmer programmer = new Programmer("Ilim Shabdanov","ilim@gmail.com",LocalDate.of(2003,10,3), Status.OWNER);
        Programmer programmer1 = new Programmer("Nuradil Djoldoshov","nurik@gmail.com",LocalDate.of(2004,10,3), Status.COLLABORATOR);
        Programmer programmer2 = new Programmer("Dastan Akbaraliev","dastan@gmail.com",LocalDate.of(2003,9,4), Status.COLLABORATOR);
        List<Programmer> programmerList = new ArrayList<>(Arrays.asList(programmer1,programmer2));
        Programmer programmer3 = new Programmer("Sanjar Abdumomunov","sanjar@gmail.com",LocalDate.of(2003,1,6), Status.COLLABORATOR);

        //========= PROJECT OBJECTS =========

        Project project = new Project("LMS","for lessons",LocalDate.of(2022,1,1),LocalDate.of(2023,1,1),2000);
        Project project1 = new Project("Facebook","for message",LocalDate.of(2020,1,1),LocalDate.of(2023,1,1),9000);
        Project project2 = new Project("You tube","for video ",LocalDate.of(2019,1,1),LocalDate.of(2025,1,1),12000);
        List<Project> projectList = new ArrayList<>(Arrays.asList(project1,project2));
        Project project3 = new Project("Taigan","for order",LocalDate.of(2021,1,1),LocalDate.of(2022,1,1),3000);


        while (true) {
            System.out.println("\n1.====== COUNTRY METHODS ======" +
                    "\n2.====== ADDRESS COUNTRY ======" +
                    "\n3.====== PROGRAMMER METHODS ======" +
                    "\n4.====== PROJECT METHODS ======");
            int num = new Scanner(System.in).nextInt();
            switch (num) {
                case 1 -> {
                    boolean isTrue = true;
                    while (isTrue) {
                        System.out.println("\n======COUNTRY METHODS=====" +
                                "\n1.SAVE COUNTRY :" +
                                "\n2.SAVE ALL COUNTRY :" +
                                "\n3.GET ALL COUNTRY :" +
                                "\n4.FIND COUNTRY BY ID :" +
                                "\n5.DELETE COUNTRY BY ID :" +
                                "\n6.DELETE ALL COUNTRY :" +
                                "\n7.UPDATE COUNTRY :" +
                                "\n8.FIND LONG DESCRIPTION :" +
                                "\n9.GET COUNT THE COUNTRY :" +
                                "\n0.EXIT() :");
                        System.out.println("ENTER BY COMMAND :");
                        int a = new Scanner(System.in).nextInt();
                        switch (a) {
                            case 1 -> System.out.println(countryService.saveCountry(country));
                            case 2 -> System.out.println(countryService.saveAllCountry(countryList));
                            case 3 -> System.out.println(countryService.getAllCountry());
                            case 4 -> {
                                System.out.println("Enter by country id :");
                                Long countryId = new Scanner(System.in).nextLong();
                                System.out.println(countryService.findCountryById(countryId));
                            }
                            case 5 -> {
                                System.out.println("Enter by country id :");
                                Long countryId = new Scanner(System.in).nextLong();
                                System.out.println(countryService.deleteCountryById(countryId));
                            }
                            case 6 -> System.out.println(countryService.deleteAllCountry());
                            case 7 -> {
                                System.out.println("Enter by country id :");
                                Long countryId = new Scanner(System.in).nextLong();
                                System.out.println(countryService.updateCountry(countryId,country3 ));
                            }
                            case 8 -> System.out.println(countryService.findLongDescription());
                            case 9 -> {
                                System.out.println("Enter by country :");
                                java.lang.String countryName = new Scanner(System.in).nextLine();
                                System.out.println(countryService.getCountTheCountry(countryName));
                            }
                            case 0 -> isTrue = false;
                        }
                    }

                } case 2 -> {
                    boolean isTrue = true;
                    while (isTrue) {
                        System.out.println("\n======ADDRESS METHODS=====" +
                                "\n1.SAVE ADDRESS :" +
                                "\n2.SAVE ALL ADDRESS :" +
                                "\n3.GET ALL ADDRESSES :" +
                                "\n4.FIND ADDRESS BY ID :" +
                                "\n5.DELETE ADDRESS BY ID :" +
                                "\n6.DELETE ALL ADDRESS :" +
                                "\n7.UPDATE ADDRESS :" +
                                "\n8.ASSIGN ADDRESS TO COUNTRY :" +
                                "\n0.EXIT() :");
                        System.out.println("ENTER BY COMMAND :");
                        int a = new Scanner(System.in).nextInt();
                        switch (a) {
                            case 1 -> System.out.println(addressService.saveAddress(address));
                            case 2 -> System.out.println(addressService.saveAllAddress(addressList));
                            case 3 -> System.out.println(addressService.getAllAddress());
                            case 4 -> {
                                System.out.println("Enter by address id :");
                                Long addressId = new Scanner(System.in).nextLong();
                                System.out.println(addressService.getAddressById(addressId));
                            }
                            case 5 -> {
                                System.out.println("Enter by address id :");
                                Long addressId = new Scanner(System.in).nextLong();
                                System.out.println(addressService.deleteAddressById(addressId));
                            }
                            case 6 -> System.out.println(addressService.deleteAllAddresses());
                            case 7 -> {
                                System.out.println("Enter by address id :");
                                Long addressId = new Scanner(System.in).nextLong();
                                System.out.println(addressService.updateAddress(addressId,address3));
                            }
                            case 8 -> {
                                System.out.println("Enter by country id :");
                                Long countryId = new Scanner(System.in).nextLong();
                                System.out.println("Enter by address id :");
                                Long addressId = new Scanner(System.in).nextLong();
                                System.out.println(addressService.assignAddressToCountry(countryId, addressId));
                            }
                            case 0 -> isTrue = false;
                        }
                    }
                }
                case 3 -> {
                    boolean isTrue = true;
                    while (isTrue) {
                        System.out.println("\n======PROGRAMMER METHODS=====" +
                                "\n1.SAVE PROGRAMMER :" +
                                "\n2.SAVE ALL PROGRAMMERS :" +
                                "\n3.GET ALL PROGRAMMER :" +
                                "\n4.FIND PROGRAMMER BY ID :" +
                                "\n5.DELETE PROGRAMMER BY ID :" +
                                "\n6.DELETE ALL PROGRAMMER :" +
                                "\n7.UPDATE PROGRAMMER :" +
                                "\n8.ADD CONSTRAINTS TO PROGRAMMER EMAIL :" +
                                "\n9.GET PROGRAMMER BY COUNTRY ID :" +
                                "\n10.GET YOUNGER PROGRAMMER :" +
                                "\n11.GET OLDER PROGRAMMER :" +
                                "\n12.ASSIGN PROGRAMMER TO ADDRESS :" +
                                "\n0.EXIT() :");
                        System.out.println("ENTER BY COMMAND :");
                        int a = new Scanner(System.in).nextInt();
                        switch (a){
                            case 1-> System.out.println(programmerService.saveProgrammer(programmer));
                            case 2-> System.out.println(programmerService.saveAllProgrammer(programmerList));
                            case 3-> System.out.println(programmerService.getAllProgrammer());
                            case 4->{
                                System.out.println("Enter by programmer id :");
                                Long programmerId = new Scanner(System.in).nextLong();
                                System.out.println(programmerService.findProgrammerById(programmerId));
                            }
                            case 5->{
                                System.out.println("Enter by programmer id :");
                                Long programmerId = new Scanner(System.in).nextLong();
                                System.out.println(programmerService.deleteProgrammerById(programmerId));
                            }
                            case 6-> System.out.println(programmerService.deleteAllProgrammer());
                            case 7->{
                                System.out.println("Enter by programmer id :");
                                Long programmerId = new Scanner(System.in).nextLong();
                                System.out.println(programmerService.updateProgrammer(programmerId,programmer3));
                            }
                            case 8-> System.out.println(programmerService.addConstraintToProgrammerEmail());
                            case 9->{
                                System.out.println("Enter by country id :");
                                Long countryId = new Scanner(System.in).nextLong();
                                System.out.println(programmerService.getProgrammerByCountryId(countryId));
                            }
                            case 10-> System.out.println(programmerService.getSmallProgrammer());
                            case 11-> System.out.println(programmerService.getOldProgrammer());
                            case 12->{
                                System.out.println("Enter by programmer id :");
                                Long programmerId = new Scanner(System.in).nextLong();
                                System.out.println("Enter by address id :");
                                Long addressId = new Scanner(System.in).nextLong();
                                System.out.println(programmerService.assignProgrammerToAddress(programmerId, addressId));
                            }case 0-> isTrue = false;

                        }

                    }
                }
                case 4 -> {
                    boolean isTrue = true;
                    while (isTrue) {
                        System.out.println("\n======PROJECT METHODS=====" +
                                "\n1.SAVE PROJECT :" +
                                "\n2.SAVE ALL PROJECTS :" +
                                "\n3.GET ALL PROJECTS :" +
                                "\n4.FIND PROJECT BY ID :" +
                                "\n5.DELETE PROJECT BY ID :" +
                                "\n6.DELETE ALL PROJECTS :" +
                                "\n7.UPDATE PROJECT :" +
                                "\n8.ASSIGN PROJECT TO PROGRAMMER :" +
                                "\n9.FIND EXPENSIVE PROJECT :" +
                                "\n10.FIND SHORTER TIME WRITER PROJECT :" +
                                "\n0.EXIT() :");
                        System.out.println("ENTER BY COMMAND :");
                        int a = new Scanner(System.in).nextInt();
                        switch (a){
                            case 1-> System.out.println(projectService.saveProject(project));
                            case 2-> System.out.println(projectService.saveAllProject(projectList));
                            case 3-> System.out.println(projectService.getAllProjects());
                            case 4->{
                                System.out.println("Enter by project id :");
                                Long projectId = new Scanner(System.in).nextLong();
                                System.out.println(addressService.getAddressById(projectId));
                            }
                            case 5->{
                                System.out.println("Enter by project id :");
                                Long projectId = new Scanner(System.in).nextLong();
                                System.out.println(addressService.deleteAddressById(projectId));
                            }
                            case 6-> System.out.println(addressService.deleteAllAddresses());
                            case 7->{
                                System.out.println("Enter by project id :");
                                Long projectId = new Scanner(System.in).nextLong();
                                System.out.println(projectService.updateProject(projectId,project3));
                            }
                            case 8->{
                                System.out.println("Enter by project id :");
                                Long projectId = new Scanner(System.in).nextLong();
                                System.out.println("Enter by programmer id :");
                                Long programmerId = new Scanner(System.in).nextLong();
                                System.out.println(projectService.assignProjectToProgrammer(projectId, programmerId));
                            }
                            case 9-> System.out.println(projectService.findExpensiveProject());
                            case 10-> System.out.println(projectService.findShorterTimeWriterProject());
                            case 0-> isTrue = false;
                        }
                    }
                }
            }
        }


    }
}
