/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.backend.main.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "EMP")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Emp.findAll", query = "SELECT e FROM Emp e")
        , @NamedQuery(name = "Emp.findByEmpno", query = "SELECT e FROM Emp e WHERE e.empno = :empno")
        , @NamedQuery(name = "Emp.findByEname", query = "SELECT e FROM Emp e WHERE e.ename = :ename")
        , @NamedQuery(name = "Emp.findByComm", query = "SELECT e FROM Emp e WHERE e.comm = :comm")})
public class Emp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EMPNO")
    @SequenceGenerator(name = "EMP_SEQ", sequenceName = "EMP_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empno;
    @Size(max = 10)
    @Column(name = "ENAME")
    private String ename;
    @Size(max = 5)
    @Column(name = "GENDER")
    private String gender;
    @Column(name = "COMM")
    private BigDecimal comm;
    @JoinColumn(name = "DEPTNO", referencedColumnName = "DEPTNO")
    @ManyToOne
    private Dept deptno;

    public Emp(String ename) {
        this.ename = ename;
    }

    public Emp() {
    }

    public Emp(Integer empno) {
        this.empno = empno;
    }

    public Integer getEmpno() {
        return empno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public BigDecimal getComm() {
        return comm;
    }

    public void setComm(BigDecimal comm) {
        this.comm = comm;
    }

    public Dept getDeptno() {
        return deptno;
    }

    public void setDeptno(Dept deptno) {
        this.deptno = deptno;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empno != null ? empno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emp)) {
            return false;
        }
        Emp other = (Emp) object;
        if ((this.empno == null && other.empno != null) || (this.empno != null && !this.empno.equals(other.empno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                '}';
    }
}
