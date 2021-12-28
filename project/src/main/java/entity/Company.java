package entity;

import javax.persistence.*;

@Entity
@Table(name="company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name", nullable = false)
    private String fName;


    @Column(name="address", nullable = false)
    private String address;

    @OneToMany (mappedBy = "company")
    private List<Employee> employees;
}
