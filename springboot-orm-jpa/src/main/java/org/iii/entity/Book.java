package org.iii.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "book")
@Data
// 預設類別名稱對應表名，加上@Table可以另外指定對應表名
public class Book {

    // GenerationType.TABLE
    // @TableGenerator(name="ID_GENERATOR",
    //　　table="t_id_generator",
    //　　pkColumnName="PK_NAME",
    //　　pkColumnValue="seed_t_customer_id",
    //　　valueColumnName="PK_VALUE",
    //　　allocationSize=20,
    //　　initialValue=10
    // )

    // GenerationType.SEQUENCE Mysql不支援
    // GenerationType.IDENTITY 對應auto_increment
    // GenerationType.AUTO 持久化引擎會根據數據庫在以上三種主鍵生成策略中選擇其中一種

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Name", length = 200, nullable = false)
    private String name;

    private String author;

    @Transient
    private String notGonnaSave;

}
