import React, {useEffect} from 'react'
import {Row, Col} from 'react-bootstrap'
import Product from '../components/Product'
import {useDispatch, useSelector} from 'react-redux'
import {listProducts} from '../actions/productActions'


function HomePage() {
    const dispatch = useDispatch()
    const productList = useSelector(state => state.productList)
    const {error, loading, products} = productList

    useEffect(() => {
        dispatch(listProducts())
    }, [dispatch])

    return (
        <div>
            <h1>Products</h1>
            {/* Handle loading or the error message upon loading problem */}
            {loading ? <h2>Loading...</h2>
                : error ? <h3>{error}</h3>
                    :
                    <Row>
                        {products.map(product => (
                            <Col key={product.id} sm={12} md={6} lg={4} xl={3}>
                                <Product product={product}/>
                            </Col>
                        ))}
                    </Row>
            }
        </div>
    )
}

export default HomePage