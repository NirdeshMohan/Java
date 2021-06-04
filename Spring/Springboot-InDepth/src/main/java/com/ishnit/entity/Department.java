package com.ishnit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long deptId;
    private String deptName;
    private String deptAddress;
    private String deptCode;

    public long getDeptId() {
        return deptId;
    }

    public void setDeptId(long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptAddress() {
        return deptAddress;
    }

    public void setDeptAddress(String deptAddress) {
        this.deptAddress = deptAddress;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public Department() {super();}

    public Department(long deptId, String deptName, String deptAddress, String deptCode) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.deptAddress = deptAddress;
        this.deptCode = deptCode;
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                ", deptAddress='" + deptAddress + '\'' +
                ", deptCode='" + deptCode + '\'' +
                '}';
    }
}
