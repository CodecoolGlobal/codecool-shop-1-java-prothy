import {
    PRODUCT_LIST_REQUEST,
    PRODUCT_LIST_SUCCESS,
    PRODUCT_LIST_FAIL,

    PRODUCT_DETAILS_REQUEST,
    PRODUCT_DETAILS_SUCCESS,
    PRODUCT_DETAILS_FAIL,

    SET_CATEGORY_FILTER,
    SET_SUPPLIER_FILTER
} from '../constants/productConstants'

export const productListReducer =
    (state = {products: []}, action) => {
        switch (action.type) {
            case PRODUCT_LIST_REQUEST:
                return {loading: true, products: []}

            case PRODUCT_LIST_SUCCESS:
                return {loading: false, products: action.payload}

            case PRODUCT_LIST_FAIL:
                return {loading: false, error: action.payload}
            default:
                return state
        }
    }


export const productDetailsReducer =
    (state = {product: []}, action) => {
        switch (action.type) {
            case PRODUCT_DETAILS_REQUEST:
                return {loading: true, product: {}}

            case PRODUCT_DETAILS_SUCCESS:
                return {loading: false, product: action.payload}

            case PRODUCT_DETAILS_FAIL:
                return {loading: false, error: action.payload}

            default:
                return state
        }
    }

    export const productFiltersReducer = (state = {category: '', supplier: ''}, action) => {
    switch(action.type) {
        case SET_CATEGORY_FILTER:
            return {...state, category: action.payload}
        case SET_SUPPLIER_FILTER:
            return {...state, supplier: action.payload}
        default:
            return state
    }
    }