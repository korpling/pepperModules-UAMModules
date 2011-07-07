/**
 * Copyright 2009 Humboldt University of Berlin, INRIA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */
package de.hu_berlin.german.korpling.saltnpepper.model.uam;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMDocument#getLayers <em>Layers</em>}</li>
 *   <li>{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMDocument#getTexts <em>Texts</em>}</li>
 *   <li>{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMDocument#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMPackage#getUAMDocument()
 * @model
 * @generated
 */
public interface UAMDocument extends EObject {
	/**
	 * Returns the value of the '<em><b>Layers</b></em>' containment reference list.
	 * The list contents are of type {@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Layer}.
	 * It is bidirectional and its opposite is '{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Layer#getUamDocument <em>Uam Document</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Layers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Layers</em>' containment reference list.
	 * @see de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMPackage#getUAMDocument_Layers()
	 * @see de.hu_berlin.german.korpling.saltnpepper.model.uam.Layer#getUamDocument
	 * @model opposite="uamDocument" containment="true"
	 * @generated
	 */
	EList<Layer> getLayers();

	/**
	 * Returns the value of the '<em><b>Texts</b></em>' containment reference list.
	 * The list contents are of type {@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Text}.
	 * It is bidirectional and its opposite is '{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Text#getUamDocument <em>Uam Document</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Texts</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Texts</em>' containment reference list.
	 * @see de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMPackage#getUAMDocument_Texts()
	 * @see de.hu_berlin.german.korpling.saltnpepper.model.uam.Text#getUamDocument
	 * @model opposite="uamDocument" containment="true"
	 * @generated
	 */
	EList<Text> getTexts();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMPackage#getUAMDocument_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMDocument#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // UAMDocument
