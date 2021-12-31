package entity;

import IdClasses.OwnerCompanyId;
import configuration.ConfigNames;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@IdClass(OwnerCompanyId.class)
@Table(name= ConfigNames.OwnerCompany.Table)
public class OwnerCompany implements Serializable {
    @Id
    @Column(name=ConfigNames.OwnerCompany.IdCompany, nullable = false)
    private long companyId;

    @Id
    @Column(name=ConfigNames.OwnerCompany.IdOwner, nullable = false)
    private long ownerId;

    @OneToOne
    @JoinColumn (name = ConfigNames.OwnerCompany.IdCompany, insertable = false, updatable = false)
    private entity.Company company;

    @ManyToOne
    @JoinColumn (name=ConfigNames.OwnerCompany.IdOwner, insertable = false, updatable = false)
    private entity.Owner owner;

    @OneToMany (mappedBy = ConfigNames.OwnerCompany.Table, fetch = FetchType.LAZY)
    private List<Employee> employee;

    public OwnerCompany(){

    }

    public OwnerCompany(long companyId, long ownerId){
        this.setOwnerId(ownerId);
        this.setCompanyId(companyId);
    }

    private void setCompanyId(long id){
        if(id<=0) throw new IllegalArgumentException("Id for company must be positive");
        this.companyId=id;
    }

    private void setOwnerId(long id){
        if(id<=0) throw new IllegalArgumentException("Id for owner must be positive");
        this.ownerId=id;
    }

    public long getCompanyId(){
        return this.companyId;
    }

    public long getOwnerId(){
        return this.ownerId;
    }

    @Override
    public String toString() {
        return "OwnerCompany{" +
                "companyId=" + companyId +
                ", ownerId=" + ownerId +
                '}';
    }
}
