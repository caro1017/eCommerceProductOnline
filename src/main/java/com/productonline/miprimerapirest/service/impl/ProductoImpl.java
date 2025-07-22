package com.productonline.miprimerapirest.service.impl;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.productonline.miprimerapirest.exception.CategoriaNoEncontradaException;
import com.productonline.miprimerapirest.exception.ProductoNoEncontradoException;
import com.productonline.miprimerapirest.exception.UsuarioNoEncontradoException;
import com.productonline.miprimerapirest.model.dao.FavoritoDao;
import com.productonline.miprimerapirest.model.dao.ProductoDao;
import com.productonline.miprimerapirest.model.dao.ProductoImagenDao;
import com.productonline.miprimerapirest.model.dao.ValoracionDao;
import com.productonline.miprimerapirest.model.dto.ProductoConImagenesDto;
import com.productonline.miprimerapirest.model.entity.Categoria;
import com.productonline.miprimerapirest.model.entity.Producto;
import com.productonline.miprimerapirest.model.entity.ProductoImagen;
import com.productonline.miprimerapirest.model.entity.Usuario;
import com.productonline.miprimerapirest.service.ICategoria;
import com.productonline.miprimerapirest.service.IProducto;
import com.productonline.miprimerapirest.service.IUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoImpl implements IProducto {

    @Autowired
    private ProductoDao productoDao;

    @Autowired
    private IUsuario usuarioService;

    @Autowired
    private ICategoria categoriaService;

    @Autowired
    private ProductoImagenDao productoImagenDao;

    @Autowired
    private ValoracionDao valoracionDao;

    @Autowired
    private FavoritoDao favoritoDao;


//    @Transactional
//    public Producto crearProductoConImagenes(Producto producto, List<String> urls) {
//
//        if (usuarioService.existsById(producto.getCedula()) == true) {
//            if (categoriaService.existsById(producto.getIdcategoria()) == true) {
//                productoDao.save(producto);
//                for (String url : urls) {
//                    ProductoImagen imagen = new ProductoImagen();
//                    imagen.setUrl(url);
//                    imagen.setProducto(producto);
//                    productoImagenDao.save(imagen);
//                }
//
//            } else {
//                throw new CategoriaNoEncontradaException("No se Agregó el Producto, La Categoria " + producto.getIdcategoria() + " No Está Registrada");
//            }
//
//        } else {
//            throw new UsuarioNoEncontradoException("No se Agregó el Producto, La Cédula " + producto.getCedula() + " No Está Registrada como Usuario.");
//        }
//        return producto;
//    }
@Transactional
    public Producto crearProductoConImagenes(ProductoConImagenesDto dto) {
    System.out.println("Entre AQUI AL PRINCIPIO!! " + dto.getCedula());
        // Validar que exista el usuario
        Usuario usuario = usuarioService.findById(dto.getCedula());
//                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));

    System.out.println("USUARIO!! " + usuario);

        // Validar que exista la categoría
//        Categoria categoria = categoriaService.existsById(dto.getIdcategoria());
//                .orElseThrow(() -> new CategoriaNoEncontradaException("Categoría no encontrada"));

        // Construir el producto
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setIdcategoria(dto.getIdcategoria());
        //producto.setCedula(dto.getCedula());
        producto.setReferencia(dto.getReferencia());
        producto.setStock(dto.getStock());
        producto.setTalla(dto.getTalla());
        producto.setMarca(dto.getMarca());
        producto.setGenero(dto.getGenero());
        producto.setColor(dto.getColor());
        producto.setGarantia(dto.getGarantia());
        producto.setUsuario(usuario); // relación
    System.out.println(producto);

        // Mapear las URLs de imágenes a entidades ProductoImagen
        List<ProductoImagen> imagenes = dto.getUrls().stream()
                .map(url -> ProductoImagen.builder()
                        .url(url)
                        .producto(producto) // establecer relación
                        .build())
                .toList();

        producto.setImagenes(imagenes);
    productoDao.save(producto);
        return producto;
    }

//    @Transactional
//    public Producto updateProducto(Integer idProducto, Producto updatedProducto, List<String> nuevasUrls) {
//        Optional<Producto> existingProducto = productoDao.findById(idProducto);
//        Producto producto = existingProducto.get();
//        if (existingProducto.isPresent()) {
//            //Producto producto = existingProducto.get();
//            producto.setNombre(updatedProducto.getNombre());
//            producto.setDescripcion(updatedProducto.getDescripcion());
//            producto.setPrecio(updatedProducto.getPrecio());
//            producto.setIdcategoria(updatedProducto.getIdcategoria());
//            //producto.setCedula(updatedProducto.getCedula());
//            producto.setReferencia(updatedProducto.getReferencia());
//            producto.setStock(updatedProducto.getStock());
//            producto.setTalla(updatedProducto.getTalla());
//            producto.setMarca(updatedProducto.getMarca());
//            producto.setGenero(updatedProducto.getGenero());
//            producto.setColor(updatedProducto.getColor());
//            producto.setGarantia(updatedProducto.getGarantia());
//
//            // Agregar solo imágenes nuevas que no estén ya registradas
//            List<String> urlsExistentes = producto.getImagenes().stream()
//                    .map(ProductoImagen::getUrl)
//                    .toList();
//
//            for (String url : nuevasUrls) {
//                if (!urlsExistentes.contains(url)) {
//                    ProductoImagen nuevaImagen = new ProductoImagen();
//                    nuevaImagen.setUrl(url);
//                    nuevaImagen.setProducto(producto);
//                    producto.getImagenes().add(nuevaImagen);
//                }
//            }
//        }
//        return productoDao.save(producto);
//    }
@Transactional
public Producto updateProducto(Integer idProducto, ProductoConImagenesDto dto) {

    Producto producto = productoDao.findById(idProducto)
            .orElseThrow(() -> new ProductoNoEncontradoException("No se encontró el producto con ID: " + idProducto));

    // Actualiza campos
    producto.setNombre(dto.getNombre());
    producto.setDescripcion(dto.getDescripcion());
    producto.setPrecio(dto.getPrecio());
    producto.setIdcategoria(dto.getIdcategoria());
    producto.setReferencia(dto.getReferencia());
    producto.setStock(dto.getStock());
    producto.setTalla(dto.getTalla());
    producto.setMarca(dto.getMarca());
    producto.setGenero(dto.getGenero());
    producto.setColor(dto.getColor());
    producto.setGarantia(dto.getGarantia());

    // Agregar nuevas imágenes si no existen ya
    List<String> urlsExistentes = producto.getImagenes().stream()
            .map(ProductoImagen::getUrl)
            .toList();

    for (String url : dto.getUrls()) {
        if (!urlsExistentes.contains(url)) {
            ProductoImagen nuevaImagen = new ProductoImagen();
            nuevaImagen.setUrl(url);
            nuevaImagen.setProducto(producto);
            producto.getImagenes().add(nuevaImagen);
        }
    }

    return productoDao.save(producto);
}


    public Iterable<Producto> obtenerTodosLosProductos() {
        return productoDao.findAll();
    }

    public List<Producto> obtenerProductosPorCedula(String cedula) {
        //return productoDao.findByCedula(cedula);
        return productoDao.findByUsuario_Cedula(cedula);
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return productoDao.findByNombreContainingIgnoreCase(nombre);
    }

    @Transactional
    @Override
    public void eliminarProductoPorId(Integer id) {
        if (productoDao.existsById(id)) {
            productoImagenDao.deleteByProductoIdproducto(id);
            valoracionDao.deleteByProductoIdproducto(id);
            favoritoDao.deleteByIdProducto(id);
            productoDao.deleteById(id);
        } else {
            throw new ProductoNoEncontradoException("Producto no encontrado con ID: " + id);
        }
    }
}
