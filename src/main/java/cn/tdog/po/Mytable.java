package cn.tdog.po;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Mytable {
	
	private Integer id;
	
	//检验名称1-30个字符
	@Size(min=1,max=30,message="{mytable.name.length}")
	private String name;
	
	@NotNull(message="{mytable.age.null}")
    private BigDecimal age;

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getAge() {
        return age;
    }

    public void setAge(BigDecimal age) {
        this.age = age;
    }
}