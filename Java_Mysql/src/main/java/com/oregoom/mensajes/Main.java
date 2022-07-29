/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oregoom.mensajes;
//api de sql

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        ListarRegistros();
    }

    static void ListarRegistros() throws SQLException {
        //conexion a la base de datos
        Connection conectar = DriverManager.getConnection(
                //cocexion local a la base de datos "mensajes_db" configuracion de zona horaria, usuario , contraseña
                "jdbc:mysql://localhost/mensajes_db?serverTimezone=UTC",
                "root",
                "123456");
        System.out.println("Conexion exitosa");

        //Recuperar datos de la base de datos "Query"
        String sql = "SELECT * FROM mensajes";
        //interfas (variable en donde se alamacena la consulta (sql))
        PreparedStatement ps = conectar.prepareStatement(sql);
        //Recuperar la consulta
        ResultSet rs = ps.executeQuery();

        //Iteracion
        while (rs.next()) {
            int id = rs.getInt("id_mensaje");
            String mensaje = rs.getString("mensaje");
            String autor = rs.getString("autor");
            String fecha = rs.getString("fecha");
            System.out.printf("%d %s %s %s \n", id, mensaje, autor, fecha);
        }
        //cerrar todos los procesos
        rs.close();
        ps.close();
        conectar.close();
    }
}
