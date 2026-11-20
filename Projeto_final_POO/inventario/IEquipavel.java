package inventario;

import Exceptions.AtributoNuloException;
import Exceptions.CapacidadeInventarioExcedidaException;
import Exceptions.ItemNaoEncontradoException;
import Exceptions.SlotInvalidoException;

public interface IEquipavel {
    void equipar(Inventario inv) throws AtributoNuloException, SlotInvalidoException, ItemNaoEncontradoException, CapacidadeInventarioExcedidaException;
    void desequipar(Inventario inv) throws AtributoNuloException, SlotInvalidoException, ItemNaoEncontradoException, CapacidadeInventarioExcedidaException;
}
