/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-26 0:21
 */

package com.group7.sanSongMall.controller.dto;

import com.group7.sanSongMall.entity.orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class orderDTO {
    orders orders;
    String productName;
    String productPic;

}
