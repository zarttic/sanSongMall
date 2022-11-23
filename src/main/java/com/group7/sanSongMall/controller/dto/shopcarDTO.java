/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-23 22:00
 */

package com.group7.sanSongMall.controller.dto;

import com.group7.sanSongMall.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class shopcarDTO {
    Product product;
    int num;
    shopcarDTO(int num,Product p){
        this.num = num;
        this.product = p;

    }
}
