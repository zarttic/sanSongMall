/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-16 21:44
 */

package com.group7.sanSongMall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private String tel;
}
