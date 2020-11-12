; Las reglas van a insertar enfermedades mentales, o indicios, según los síntomas del paciente
; ? indica que es una variable
; el valor del slot se asigna a una varible, y el valor de esa variable es igual a 

(import Negocio.Entidades.Sintoma)
(deftemplate Sintoma (declare (from-class Sintoma)))

(batch Clips/sintomas.clp)

(defrule inicio
=>
(printout t "Iniciando inferencia..." crlf)
)

(defrule indicios_ansiedad
(Sintoma 
(nombre ?nombre&:(eq ?nombre "\"preocupacion_excesiva\"")) (respuesta ?res&:(eq ?res TRUE)))
(Sintoma 
(nombre ?nombre1&:(eq ?nombre1 "\"pensar_peores_resultados\"")) (respuesta ?res&:(eq ?res TRUE)))
(Sintoma 
(nombre ?nombre2&:(eq ?nombre2 "\"dificultad_concentracion\"")) (respuesta ?res&:(eq ?res TRUE)))
(Sintoma
(nombre ?nombre3&:(eq ?nombre3 "\"incapacidad_relajarse\"")) (respuesta ?res&:(eq ?res TRUE)))
    =>
(assert(indicios_ansiedad Indicios-de-ansiedad))
)

(defrule trastorno_ansiedad
(indicios_ansiedad ?)
(Sintoma 
(nombre ?nombre&:(eq ?nombre "\"dificultad_control_preocupaciones\"")) (respuesta ?res&:(eq ?res TRUE)))
(Sintoma 
(nombre ?nombre1&:(eq ?nombre1 "\"temperamento_indeciso\"")) (respuesta ?res&:(eq ?res TRUE)))
(Sintoma 
(nombre ?nombre2&:(eq ?nombre2 "\"situaciones_amenazantes\"")) (respuesta ?res&:(eq ?res TRUE)))
(Sintoma 
(nombre ?nombre3&:(eq ?nombre3 "\"sintomas_fisicos\"")) (respuesta ?res&:(eq ?res TRUE)))
    =>
(assert(trastorno_ansiedad Trastorno-de-ansiedad))
)

(defrule indicios_depresion
(Sintoma 
(nombre ?nombre&:(eq ?nombre "\"sentimientos_tristeza\"")) (respuesta ?res&:(eq ?res TRUE)))
(Sintoma 
(nombre ?nombre1&:(eq ?nombre1 "\"perdida_interes_placer\"")) (respuesta ?res&:(eq ?res TRUE)))
(Sintoma 
(nombre ?nombre2&:(eq ?nombre2 "\"alteraciones_sueño\"")) (respuesta ?res&:(eq ?res TRUE)))
(Sintoma 
(nombre ?nombre3&:(eq ?nombre3 "\"cansancio_fatiga\"")) (respuesta ?res&:(eq ?res TRUE)))
    =>
(assert(indicios_depresion Indicios-de-depresión))
)

(defrule trastorno_depresion
(indicios_depresion ?)
(Sintoma 
(nombre ?nombre&:(eq ?nombre "\"aumento_perdida_peso\"")) (respuesta ?res&:(eq ?res TRUE)))
(Sintoma 
(nombre ?nombre1&:(eq ?nombre1 "\"sentimientos_culpa_fracaso\"")) (respuesta ?res&:(eq ?res TRUE)))
(Sintoma 
(nombre ?nombre2&:(eq ?nombre2 "\"pensamiento_suicidio\"")) (respuesta ?res&:(eq ?res TRUE)))
    =>
(assert(trastorno_depresion Trastorno-de-depresión))
)

(defrule trastorno_obsesivo
(Sintoma 
(nombre ?nombre&:(eq ?nombre "\"pensamientos_repetitivos\"")) (respuesta ?res&:(eq ?res TRUE)))
(Sintoma 
(nombre ?nombre1&:(eq ?nombre1 "\"temor_germenes_contagio\"")) (respuesta ?res&:(eq ?res TRUE)))
(Sintoma 
(nombre ?nombre2&:(eq ?nombre2 "\"pensamientos_prohibidos_indeseados\"")) (respuesta ?res&:(eq ?res TRUE)))
(Sintoma 
(nombre ?nombre3&:(eq ?nombre3 "\"pensamientos_agresivos\"")) (respuesta ?res&:(eq ?res TRUE)))
    =>
(assert(trastorno_obsesivo Trastorno-obsesivo))
)

(defrule trastorno_compulsivo
(Sintoma 
(nombre ?nombre&:(eq ?nombre "\"comportamientos_repetitivos\"")) (respuesta ?res&:(eq ?res TRUE)))
(Sintoma 
(nombre ?nombre1&:(eq ?nombre1 "\"objetivo_reducir_ansiedad\"")) (respuesta ?res&:(eq ?res TRUE)))
    =>
(assert(trastorno_compulsivo Trastorno-compulsivo))
)

(defrule trastorno_obsesivo_compulsivo
(trastorno_obsesivo ?)
(trastorno_compulsivo ?)
(Sintoma 
(nombre ?nombre&:(eq ?nombre "\"obsesiones_compulsiones_tiempo\"")) (respuesta ?res&:(eq ?res TRUE)))
(Sintoma 
(nombre ?nombre1&:(eq ?nombre1 "\"obsesiones_compulsiones_drogas\"")) (respuesta ?res&:(eq ?res TRUE)))
(Sintoma 
(nombre ?nombre2&:(eq ?nombre2 "\"incapacidad_control_pensamientos\"")) (respuesta ?res&:(eq ?res TRUE)))
(Sintoma 
(nombre ?nombre3&:(eq ?nombre3 "\"necesidad_alineacion_orden\"")) (respuesta ?res&:(eq ?res TRUE)))
    =>
(assert(trastorno_obsesivo_compulsivo Trastorno-obsesivo-compulsivo))
)

(defrule mania_hipomania
(Sintoma 
(nombre ?nombre&:(eq ?nombre "\"sensacion_bienestar_euforia\"")) (respuesta ?res&:(eq ?res TRUE)))
(Sintoma 
(nombre ?nombre1&:(eq ?nombre1 "\"descontrol_temperamento\"")) (respuesta ?res&:(eq ?res TRUE)))
(Sintoma 
(nombre ?nombre2&:(eq ?nombre2 "\"animo_irritable\"")) (respuesta ?res&:(eq ?res TRUE)))
(Sintoma 
(nombre ?nombre3&:(eq ?nombre3 "\"aumento_energia\"")) (respuesta ?res&:(eq ?res TRUE)))
(Sintoma 
(nombre ?nombre4&:(eq ?nombre4 "\"innecesidad_dormir\"")) (respuesta ?res&:(eq ?res TRUE)))
(Sintoma 
(nombre ?nombre5&:(eq ?nombre5 "\"irritacion_distraccion_extrema\"")) (respuesta ?res&:(eq ?res TRUE)))
    =>
(assert(mania_hipomania Mania-Hipomania))
)

(defrule depresion_mayor
(Sintoma 
(nombre ?nombre&:(eq ?nombre "\"animo_depresivo\"")) (respuesta ?res&:(eq ?res TRUE)))
(Sintoma 
(nombre ?nombre1&:(eq ?nombre1 "\"perdida_interes_placer\"")) (respuesta ?res&:(eq ?res TRUE)))
(Sintoma 
(nombre ?nombre2&:(eq ?nombre2 "\"aumento_perdida_peso\"")) (respuesta ?res&:(eq ?res TRUE)))
(Sintoma 
(nombre ?nombre3&:(eq ?nombre3 "\"sentimientos_inutilidad_culpa\"")) (respuesta ?res&:(eq ?res TRUE)))
(Sintoma 
(nombre ?nombre4&:(eq ?nombre4 "\"cansancio_fatiga\"")) (respuesta ?res&:(eq ?res TRUE)))
(Sintoma 
(nombre ?nombre5&:(eq ?nombre5 "\"insomnio_dormir_demasiado\"")) (respuesta ?res&:(eq ?res TRUE)))
(Sintoma 
(nombre ?nombre6&:(eq ?nombre6 "\"pensamiento_suicidio\"")) (respuesta ?res&:(eq ?res TRUE)))
    =>
(assert(depresion_mayor Depresion-mayor))
)

(defrule trastorno_bipolaridad
(mania_hipomania ?)
(depresion_mayor ?)
    =>
(assert(trastorno_bipolaridad Trastorno-de-bipolaridad))
)

(defrule sano
(not (indicios_ansiedad ?))
(not (indicios_depresion ?))
(not (trastorno_obsesivo ?))
(not (trastorno_compulsivo ?))
(not (mania_hipomania ?))
(not (depresion_mayor ?))
=>
(assert (sano Sano))
)
