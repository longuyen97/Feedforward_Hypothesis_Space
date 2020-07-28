package de.longuyen.neuronalnetwork.activations

import org.nd4j.linalg.api.ndarray.INDArray
import org.nd4j.linalg.factory.Nd4j
import org.nd4j.linalg.ops.transforms.Transforms

class Softmax : Activation(){
    override fun forward(x: INDArray): INDArray {
        val e = Transforms.exp(x)
        return e.div(e.sum(true, 0))
    }

    override fun backward(x: INDArray): INDArray {
        val e = this.forward(x)
        val eVector = x.reshape(x.shape()[0], 1)
        val eMatrix = Nd4j.tile(eVector, e.shape()[0].toInt())
        return Nd4j.diag(e).sub(eMatrix.mul(eMatrix.transpose()))
    }

}