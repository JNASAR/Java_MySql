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
        EliminarRegistros(1);
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
    static void InsertarRegistros(String mensaje, String autor) throws SQLException {
        //conexion a la base de datos
        Connection conectar = DriverManager.getConnection(
                //cocexion local a la base de datos "mensajes_db" configuracion de zona horaria, usuario , contraseña
                "jdbc:mysql://localhost/mensajes_db?serverTimezone=UTC",
                "root",
                "123456");
        System.out.println("Conexion exitosa");

        //Insertar datos de la base de datos "Query"
        String sql = "INSERT INTO mensajes(mensaje,autor,fecha)VALUES(?,?,CURRENT_DATE())";
        //interfas (variable en donde se alamacena la consulta (sql))
        PreparedStatement ps = conectar.prepareStatement(sql);
        //Para insertar registros no se usa ResultSet 
        //Usamos "PreparedStatement = ps", para recuperar los datos a cargar/insertar
        ps.setString(1, mensaje);
        ps.setString(2, autor);
        //actualizar base
        ps.executeUpdate();   
        //cerrar todos los procesos
        ps.close();
        conectar.close();
    }
    static void EditarRegistros(String mensaje, String autor,int id) throws SQLException {
        //conexion a la base de datos
        Connection conectar = DriverManager.getConnection(
                //cocexion local a la base de datos "mensajes_db" configuracion de zona horaria, usuario , contraseña
                "jdbc:mysql://localhost/mensajes_db?serverTimezone=UTC",
                "root",
                "123456");
        //System.out.println("Conexion exitosa");

        //Editar datos de la base de datos "Query"
        String sql = "UPDATE mensajes SET mensaje=?,autor=? WHERE id_mensaje =?";
        //interfas (variable en donde se alamacena la consulta (sql))
        PreparedStatement ps = conectar.prepareStatement(sql);
        //Para insertar registros no se usa ResultSet 
        //Usamos "PreparedStatement = ps", para recuperar los datos a cargar/insertar
        ps.setString(1, mensaje);
        ps.setString(2, autor);
        ps.setInt(3, id);
        //actualizar base
        ps.executeUpdate();   
        //cerrar todos los procesos
        ps.close();
        conectar.close();
    }
    static void EliminarRegistros(int id) throws SQLException {
        //conexion a la base de datos
        Connection conectar = DriverManager.getConnection(
                //cocexion local a la base de datos "mensajes_db" configuracion de zona horaria, usuario , contraseña
                "jdbc:mysql://localhost/mensajes_db?serverTimezone=UTC",
                "root",
                "123456");
        //System.out.println("Conexion exitosa");

        //Editar datos de la base de datos "Query"
        String sql = "DELETE FROM mensajes WHERE id_mensaje=?";
        //interfas (variable en donde se alamacena la consulta (sql))
        PreparedStatement ps = conectar.prepareStatement(sql);
        //Para insertar registros no se usa ResultSet 
        //Usamos "PreparedStatement = ps", para recuperar los datos a cargar/insertar
        ps.setInt(1, id);
        //actualizar base
        ps.executeUpdate();   
        //cerrar todos los procesos/flujos
        ps.close();
        conectar.close();
    }
}
