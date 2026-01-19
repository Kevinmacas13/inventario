SELECT 
    ped.numero_serial AS codigo_pedido,
    ped.fecha AS fecha_pedido,
    ped.estado AS estado_pedido,
    ped.proveedor,
    detPed.codigo_serial AS codigo_detalle,
    detPed.subtotal,
    detPed.cantidad_recibida,
    detPed.cantidad_solicitada,
    detPed.producto,
    estPed.codigo AS codigo_estado,
    estPed.nombre AS nombre_estado
FROM cabecera_pedido AS ped
JOIN detalle_pedido AS detPed 
    ON ped.numero_serial = detPed.cabecera_pedido
JOIN estado_pedidos AS estPed 
    ON ped.estado = estPed.codigo
WHERE UPPER(ped.proveedor) LIKE '1792285747001';

SELECT 
    ped.numero_serial AS codigo_pedido,
    ped.fecha AS fecha_pedido,
    ped.estado AS estado_pedido,
    ped.proveedor,
    estPed.codigo AS codigo_estado,
    estPed.nombre AS nombre_estado
FROM cabecera_pedido AS ped
JOIN estado_pedidos AS estPed 
    ON ped.estado = estPed.codigo
