package com.TejaITB2.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDtls {

	private CustomerDto customerDto;
	private ItemsDto itemsDto;
	private OrdersDto orders;
	public CustomerDto getCustomerDto() {
		return customerDto;
	}
	public void setCustomerDto(CustomerDto customerDto) {
		this.customerDto = customerDto;
	}
	public ItemsDto getItemsDto() {
		return itemsDto;
	}
	public void setItemsDto(ItemsDto itemsDto) {
		this.itemsDto = itemsDto;
	}
	public OrdersDto getOrders() {
		return orders;
	}
	public void setOrders(OrdersDto orders) {
		this.orders = orders;
	}
}
