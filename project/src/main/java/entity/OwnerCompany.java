package entity;

import IdClasses.OwnerCompanyId;
import configuration.ConfigNames;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@IdClass(OwnerCompanyId.class)
@Table(name= ConfigNames.OwnerCompany.Table)
public class OwnerCompany{

    @Id
    @Column(name=ConfigNames.OwnerCompany.IdOwner, nullable = false)
    private long ownerId;

    @Id
    @Column(name=ConfigNames.OwnerCompany.IdCompany, nullable = false)
    private long companyId;

    @OneToOne
    @JoinColumn (name = ConfigNames.OwnerCompany.IdCompany, insertable = false, updatable = false)
    private entity.Company company;

    @ManyToOne
    @JoinColumn (name=ConfigNames.OwnerCompany.IdOwner, insertable = false, updatable = false)
    private entity.Owner owner;

    public OwnerCompany(){

    }

    public OwnerCompany(long ownerId, long companyId){
        this.setOwnerId(ownerId);
        this.setCompanyId(companyId);
    }

    private void setOwnerId(long id){
        if(id<=0) throw new IllegalArgumentException("Id for owner must be positive");
        this.ownerId=id;
    }

    private void setCompanyId(long id){
        if(id<=0) throw new IllegalArgumentException("Id for company must be positive");
        this.companyId=id;
    }

    public long getOwnerId(){
        return this.ownerId;
    }

    public long getCompanyId(){
        return this.companyId;
    }
}
