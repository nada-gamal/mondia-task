package eg.com.mondia.restapi.dto;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;


@Component
public class ServiceDto implements java.io.Serializable {

	private BigDecimal id;
	private BigDecimal productId;
	private BigDecimal operatorId;
	private String name;
	private String type;
	private BigDecimal operatorServiceId;
	private BigDecimal operatorPackageId;

	public ServiceDto() {
	}

	public ServiceDto(BigDecimal id, String name) {
		this.id = id;
		this.name = name;
	}

	public ServiceDto(BigDecimal id, BigDecimal productId, BigDecimal operatorId, String name, String type,
			BigDecimal operatorServiceId, BigDecimal operatorPackageId) {
		this.id = id;
		this.productId = productId;
		this.operatorId = operatorId;
		this.name = name;
		this.type = type;
		this.operatorServiceId = operatorServiceId;
		this.operatorPackageId = operatorPackageId;
	}

	
	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	
	public BigDecimal getProductId() {
		return this.productId;
	}

	public void setProduct(BigDecimal productId) {
		this.productId = productId;
	}

	public BigDecimal getOperatorId() {
		return this.operatorId;
	}

	public void setOperatorId(BigDecimal operatorId) {
		this.operatorId = operatorId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getOperatorServiceId() {
		return this.operatorServiceId;
	}

	public void setOperatorServiceId(BigDecimal operatorServiceId) {
		this.operatorServiceId = operatorServiceId;
	}

	public BigDecimal getOperatorPackageId() {
		return this.operatorPackageId;
	}

	public void setOperatorPackageId(BigDecimal operatorPackageId) {
		this.operatorPackageId = operatorPackageId;
	}

}
